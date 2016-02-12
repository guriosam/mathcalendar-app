package br.com.gods.mathcalendar.communication;


public abstract class MathCalendarAbstractService {

    // Essa prorpiedade é injetada através do MathCalendarServiceFactory para obrigar que todos os serviços sejam construídos por lá,
    // por isso a visibilidade dele é private e não possui método set
    private MathCalendarApiClient apiClient;

    protected MathCalendarApiClient getApiClient() {
        if (apiClient == null) {
            throw new RuntimeException("A construção deste serviço deve passar pelo ServiceFactory");
        }
        return apiClient;
    }

    /*protected static class DefaultApiCallback<T> implements Callback<T> {

        private MathCalendarServiceCallback<T> serviceCallback;

        public DefaultApiCallback(MathCalendarServiceCallback<T> serviceCallback) {
            this.serviceCallback = serviceCallback;
        }

        @Override
        public void success(T t, Response response) {
            if (this.serviceCallback != null) {
                this.serviceCallback.success(t);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            if (this.serviceCallback != null) {
                MathCalendarServiceError serviceError = new MathCalendarServiceError(MathCalendarApplication
                        .getMathCalendarApplicationContext()
                        .getString(R.string
                                .wikipass_error_server), error);

                this.serviceCallback.failure(serviceError);
            }
        }
    }*/
}
