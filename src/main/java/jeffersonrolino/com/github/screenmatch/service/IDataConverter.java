package jeffersonrolino.com.github.screenmatch.service;

public interface IDataConverter {
    <T> T convertData(String json, Class<T> tClass);
}
