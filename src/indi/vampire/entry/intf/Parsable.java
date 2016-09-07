package indi.vampire.entry.intf;

/**
 * 序列化存储的对象后，在反序列化时，如果有需要解析器解析的字段，需要先调用初始化方法，初始化解析器，来解析标志位字段。
 * 读取反序列化获得的对象后，如果需要再次序列化到内存中，则需使用写入方法，来将解析器的缓存数据写入对象中。
 * @author guyifan
 */
public interface Parsable {
	/**
	 * 初始化方法，用于初始化解析一些参数和变量
	 */
	public void parse();
	
	/**
	 * 写入方法，用于将解析器的数据写入原始对象中
	 */
	public void flush();
}
