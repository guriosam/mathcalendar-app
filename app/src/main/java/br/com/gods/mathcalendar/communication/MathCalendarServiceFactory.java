package br.com.gods.mathcalendar.communication;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class MathCalendarServiceFactory {

    private MathCalendarApiClient apiClient;
    private Map<Class<?>, Class<?>> avaiableServices;

    public MathCalendarServiceFactory(MathCalendarApiClient apiClient) {
        this.apiClient = apiClient;
        this.avaiableServices = new HashMap<>();
    }

    public <T> void registerService(Class<T> serviceInterface, Class<? extends T> serviceImplementation){
        avaiableServices.put(serviceInterface, serviceImplementation);
    }

    public <T> T createService(Class<T> serviceClass){

        Class<T> serviceImplementationClass = (Class<T>) avaiableServices.get(serviceClass);

        if(serviceImplementationClass == null){
            throw new RuntimeException("Não existe nenhum serviço registrado em conformidade com a classe: " + serviceClass.getSimpleName());
        }

        T service = null;
        try {
            service = serviceImplementationClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Erro ao instanciar o serviço da classe: " + serviceClass.getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Erro ao instanciar o serviço da classe: " + serviceClass.getSimpleName() + " - Não existe construtor público",
                    e);
        }

        if(service instanceof MathCalendarAbstractService){
            MathCalendarAbstractService abstractService = (MathCalendarAbstractService)service;
            try {
                Field apiClientField = MathCalendarAbstractService.class.getDeclaredField("apiClient");
                apiClientField.setAccessible(true);
                apiClientField.set(abstractService, apiClient);
                apiClientField.setAccessible(false);
            } catch (Exception e){
                throw new RuntimeException("Erro ao injetar o apiClient na classe "  + serviceClass.getSimpleName(), e);
            }
        }

        return service;
    }
}
