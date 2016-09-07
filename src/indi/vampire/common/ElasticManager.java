package indi.vampire.common;

import indi.vampire.common.DefaultConstants.DefaultElastic;
import indi.vampire.common.abs.Component;
import indi.vampire.common.utils.StringUtils;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public final class ElasticManager extends Component {
	private static ElasticManager instance;
	public static synchronized ElasticManager getInstance(){
		if(instance == null){
			instance = new ElasticManager();
		}
		return instance;
	}
	
	private final String ip;
	private final Integer port;
	
	private ElasticManager(){
		ip = config.get(DefaultElastic.IP);
		port = StringUtils.parseInteger(config.get(DefaultElastic.PORT));
		
		_log.debug("load elastic address -> " + ip + ":" + port);
	}
	
	public Client get(){
		TransportClient client = new TransportClient();
		client.addTransportAddress(new InetSocketTransportAddress(ip, port));
		return client;
	}
	
	public void release(Client client){
		close(client);
	}
}
