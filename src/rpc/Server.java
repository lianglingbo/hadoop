package rpc;

public interface Server {
    public void stop();
    public void start() throws Exception;
    public void register(Class serviceInterface,Class impl);
    public boolean isRunning();
    public int getPort();
}
