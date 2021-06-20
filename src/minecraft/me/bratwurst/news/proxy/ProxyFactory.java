package me.bratwurst.news.proxy;

import io.netty.bootstrap.ChannelFactory;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.socket.oio.OioSocketChannel;
import java.net.Proxy;
import java.net.Socket;

public class ProxyFactory
        implements ChannelFactory<OioSocketChannel> {
    private Proxy proxy;

    @Override
    public OioSocketChannel newChannel() {
        return new OioSocketChannel(new Socket(this.proxy));
    }

    public ProxyFactory(Proxy proxy) {
        this.proxy = proxy;
    }
}
