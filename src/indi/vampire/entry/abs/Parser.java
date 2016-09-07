package indi.vampire.entry.abs;

/**
 * 抽象解析器，用于解析标志位类型的属性
 * @author guyifan
 */
public abstract class Parser {
	protected Integer code;		// 需要解析的属性
	
	/**
	 * 初始化方法，默认会将带解析的值置为0，并按照0解析其他属性
	 */
	public Parser(){
		code = 0;
		resolve();
	}
	
	/**
	 * 获取解析后重组的属性
	 * @return
	 */
	public Integer fetchCode(){
		combine();
		return code;
	}
	
	/**
	 * 设定待解析的属性
	 * @param code 待解析的属性
	 */
	public void putCode(Integer code){
		this.code = code == null ? 0 : code;
		resolve();
	}
	
	/**
	 * 解析方法，用于解析属性
	 */
	abstract protected void resolve();
	
	/**
	 * 重组方法，用于将解析结果重组成属性
	 */
	abstract protected void combine();
}
