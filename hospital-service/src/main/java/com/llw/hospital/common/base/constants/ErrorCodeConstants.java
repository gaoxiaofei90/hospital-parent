package com.llw.hospital.common.base.constants;

/**
 * @author wendellpeng
 * @Title: JWTFilter
 * @ProjectName gov-parent
 * 错误码常量
 * @date 2018/8/2719:57
 */
public enum ErrorCodeConstants {
	FAILD(-99,"处理失败"),
	JSON_LOGIN(-1,"需要登录"),
	JSON_FORBIDDEN(-2,"无权限"),
	JSON_CONCURRENT(-3,"并发上限"),
	JSON_ERROR(-4,"未知异常"),
	JSON_GOSUPERIOR(-5,"请求往上级部门转发"),
	FNID_NOT_FOUND(-6,"没有对应功能号"),
	JSON_PARSE_ERROR(-7,"JSON解析错误,请检查报文是否符合规范"),
	REQUEST_UNVALIDAT(-8,"请求非法"),
	INTF_JSON_FORBIDDEN(-9,"无接口权限"),
	SUCCESS(0,"处理成功"),

	//系统错误
	PARAM_INCOMPLETE(1,"缺少参数"),
	PASSWORD_INCORRECT(2,"密码不正确"),
	NEWANDCOM_PASSWORD_INCONSISTENT(3,"新密码和确认密码不一致"),
	PARSEDATE_ERROR(4,"日期转换错误"),
	OPERATOR_NOT_EXISTS(5,"用户不存在"),
	VERIFICATION_INCORRECT(6,"验证码不正确"),
	ADMIN_NOT_LOGIN(7,"无法使用admin用户登录"),
	PASSWORD_WEAK(8,"密码必须包含字母、数字和除转义字符（“\\”）、空格（“ ”）外的特殊字符,长度在8-20位之间"),
	VALIDATE_EXPIRED(9,"验证码已过期"),
	SOCKET_BUSY(10,"算法正忙"),

	OPERATOR_TEL_BEBOUND(11,"手机号码已经绑定其它用户绑定"),
	
	OPERATOR_DEVICE_BEBOUND(20,"对应工号不能使用该台设备"),
	
	IDCARD_ILLEGLE(19,"证件号码非法"),
	SOCKET_CONNECTION_ERROR(22,"算法连接失败"),
	BATCH_ID_CAN_NOT_BE_NULL(23,"批次不能为空"),
	BATCH_ID_NOT_NULL_LESS_50(24,"批次不能为空且数量不能多于50个"),
	PARAM_INVALID(30,"参数配置问题"),
	BATCH_IS_DONE(31,"批次已经结束"),
	UPGRADEINFO_NOT_FOUND(32,"找不到升级包"),
	UPGRADEINFO_BUSY(321,"服务器繁忙，请稍后再试"),
	UPGRADEINFO_MAC_CONFLICT(322,"相同MAC的机器正在下载"),
	BIOTYPE_NOT_SUPPORTED(33,"不支持的生物类型"),
//	CONFIG_ERROR(1032,"配置参数配置错误"),
	TERMINAL_NOT_EXISTS(34,"此终端不存在"),
	VERSION_CURRENT(35,"此版本为设备的当前版本"),
	VERSION_USERD(36,"此版本已被设备使用"),
	VERSION_OLD(37,"此版本是被设备使用过的老版本，不能删除"),
	CONFIG_UNIONPAY_PARAM_NOT_FOUND(38,"找不到对应银联相关参数"),
	UPLOAD_MONITOR_INFORMATION_EXCEPTION(39,"上传监控信息异常"),
	COMMAND_LOG_NOT_EXISTS(40,"命令记录不存在"),
	ALGVERSION_NOT_SUPPORT(41,"算法版本不支持"),
	//人员类错误码1XX
	STAFF_NOT_EXISTS(100,"没有参保信息"),
	STAFF_STATUS_ERROR(102,"参保人状态不正确"),
	STAFF_NOT_INSURE(103,"用户未参保"),
	STAFF_EXISTS(104,"参保人员已经存在"),
	STAFF_NOT_UNIQU(105,"参保人员不唯一"),
	STAFF_NOT_SYNC(106,"人员未同步，不允许认证！"),
	STAFF_ADD_FAIL(107,"平台人员新增失败"),
	SUB_STAFF_ADD_FAIL(108,"子系统人员新增失败"),
	
	TELEPHONE_FORMAT_ERROR(109,"电话号码格式错误 "),
	EMAIL_FORMAT_ERROR(109,"邮箱格式错误 "),
	ADDR_LENGTH_ERROR(110,"地址长度超出范围"),
	PERSON_IS_DEATH(111,"人员状态异常"),
	PAY_GRANT_ERROR(112,"待遇发放状态异常"),
	PERSON_NOT_EXISTS(113,"没有人员信息"),
	ID_CARD_UNIQU(199,"抱歉，您的身份证号码重复，请咨询当地社保机构处理"),
	
	//模板类错误码2XX
	MODEL_NOT_EXISTS(200,"没有模板信息"),
	MODEL_FORBID(201,"模板被禁用"),
	MODEL_EXISTS(202,"无法重复建模"),
	FACE_DETECT_ERROR(203,"人脸检测失败"),
	APPOINT_MODEL_NOT_EXISTS(204,"预约模板不存在"),
	APPOINT_MODEL_EXISTS(205,"预约模板已经存在"),
	MODEL_STATUS_ERROR(206,"模板状态不正确"),
	MODEL_PIC_NOT_EXISTS(207,"没有模板照片信息"),
	MODEL_DELETE_FORBID(208,"非本人建模"),
	MODEL_DELETE_TIMEOUT(209,"删除超时"),
	MODEL_VERSION_NOT_MATCH(210,"算法版本不一致"),
	MODEL_PIC_IVALID(211,"模板照数据格式错误"),
	FEATURE_EXTRACT_FAIL(224,"抽取特征失败"),
	CREATEMODEL_NOT_ALLOWD(212,"不允许建模"),
	BIOTYPE_SUPPORTED_ALREADY_MODELD(213,"支持的生物类型已经全建模，不需要再建模了"),
	MODEL_IMAGE_INVALID(214,"图片数据不合法"),
	MODEL_IMAGE_SIZE_INVALIDE(215,"图片大小不合法"),
	MODEL_IMAGE_UNCLEAR(216,"图像模糊"),
	CREATE_MODEL_FAIL(217,"建模失败"),
	CREATE_MODEL_FAIL_NONE_MODEL(218,"无法完成建模,没有上传模板"),
	CREATE_MODEL_FAIL_MUTIL_MODEL(219,"建模失败，可能存在多张人脸"),
	IDCARD_IMAGE_NOT_EXISTS(220,"证件照不存在"),
	MODEL_AREA_CODE_NOT_EXISTS(221,"没有行政区划数据，无法建模"),
	PICS_LACKOF_FACE_POSITION(223,"照片缺少人脸位置"),
	PICS_LACKOF_FINGER_POSITION(222,"照片缺少指位信息"),
	IDENTIFY_INNEED(300,"不需要认证"),
	APPEAL_DUPLICATED(301,"不能重复申诉"),
	IDENTIFY_TIMES_LIMIT(305,"超过每日最大认证次数"),
	IDENTIFY_QUALITY_EVALUATION_LIMIT(306,"证件照和模板照片相似度低，不允许认证"),
	IDENTIFY_INFO_NOT_EXISTS(308,"没有认证信息"),
	COMPARE_FAIL(311,"比对失败"),
	IDENTIFY_NOT_ALLOWD(323,"不允许认证"),
	LIVE_RESULT_NOT_EXISTS(324,"缺少活体结果"),
	COMPARE_RESULT_NOT_EXISTS(325,"缺少比对记录"),
	COMPARE_SYSNO_MISMATCHING(330,"认证人员编号与检查是否可以认证时保存的人员编号不匹配"),
	IDENTIFY_AREA_CODE_NOT_EXISTS(331,"没有行政区划数据，无法认证"),
	
	//辅助类错误码
	PLAN_DATA_NOT_EXISTS(401,"数据包不存在"),
	PLAN_DATA_NOT_GENERATED(412,"数据包未生成"),
	PLAN_DATA_GENERATION(413,"数据包生成中"),
	PLAN_DATA_GENERATION_FAIL(414,"数据包生成失败"),
	ACCESS_ADDRESS_NOT_EXISTS(415,"管理后台地址未配置"),
	
	//算法交互类错误码 9XX
	ALGORITHM_TYPE_PARAMETER_NOT_VALID(901,"算法类型参数不合法"),
	ALGORITHM_FUNCTION_PARAMETER_NOT_VALID(902,"功能号参数不合法"),
	ALGORITHM_CHARACTERISTIC_NUM_NOT_VALID(903,"特征组数量不合法"),
	ALGORITHM_CHARACTERISTIC_DATA_NOT_VALID(904,"特征组数据不合法"),
	ALGORITHM_CHARACTERISTIC_TWO_NUM_NOT_VALID(905,"特征组2数量不合法"),
	ALGORITHM_CHARACTERISTIC_TWO_DATA_NOT_VALID(906,"特征组2数据不合法"),
	ALGORITHM_IMAGE_NUM_NOT_VALID(907,"图像组数量不合法"),
	ALGORITHM_IMAGE_DATA_NOT_VALID(908,"图像组数据不合法"),
	ALGORITHM_IMAGE_TWO_NUM_NOT_VALID(909,"图像组2数量不合法"),
	ALGORITHM_IMAGE_TWO_DATA_NOT_VALID(910,"图像组2数据不合法"),
	ALGORITHM_OTHER_ELEVEN(911,"其他"),
	ALGORITHM_RETURN_ERROR(912,"算法返回异常"),
	ALGORITHM_WRITE_TIMEOUT(913,"算法超时"),
	ALGORITHM_READ_TIMEOUT(914,"算法超时"),
	ALGORITHM_NONE_MINSCORE(915,"提取特征缺少最低分"),
	//子系统业务相关错误 以子系统编号打头  
	
	//养老1XXX
	
	//报表
	BB_IS_QUERY_FOR_RESULT(501,"正在查询数据，整个过程可能需要5到10分钟，请过下在查看结果"),
	BB_EXPOET_FOR_RESULT(502,"导出数据前请先查询出要导出的数据"),
	
	//教育考试8XXX
    IDENTIFY_PLAN_NOT_EXISTS(8001,"考试计划不存在"),
    IDENTIFY_PLAN_PERSON_NOT_EXISTS(8002,"考试人员不存在"),
    KSPERSON_NOT_EXISTS(8003,"没有此考生信息"),
    KSPERSON_IS_REGISTER(8004,"此考生已注册"),
    SUPERVISIOR_NOT_EXISTS(8005,"对不起，查询不到该监考老师的信息"),
    NOT_IDENTIFY_PLAN_TIME(8006,"不在当前考试计划认证时间内，不允许认证"),
    NOT_KS_REGISTER(8007,"此考生未报名该科目"),
    NOT_AUDIT_KS_REGISTER(8008,"网络报名考生资料核验未通过"),
    NOT_IDENTIFY_SUBJECT_TIME(8009,"不在科目考试时间内，不允许认证"),
    IDENTIFY_PLAN_NOT_AUDIT(8010,"考试计划未审核通过"),
    IDENTIFY_PLAN_NOT_AUDIT_ERROR(8011,"考试计划审核失败"),
    IDENTIFY_PLAN_NOT_ZT(8012,"考试计划已暂停"),
    IDENTIFY_PLAN_NOT_JS(8013,"考试计划已结束"),
    IDENTIFY_PLAN_ERROR_NAME(8014,"报名所用姓名与考生管理登记的信息不符"),
    IDENTIFY_PLAN_ERROR_IDCARD(8015,"报名所用身份证与考生管理登记的信息不符"),
	
	//医保7XXX
	MEDICAL_STATUS_ERROR(7010,"就医状态不正确"),
	MEDICAL_RECORD_NOT_EXISTS_IN_THIRD(7012,"没有就医记录"),
	MEDICAL_STATUS_DONE_ALREADY(7014,"已出院"),
	MEDICAL_NOT_LOCAL_PATIENT(7016,"非本医院病人不允许登记"),
	AKE111_NOT_EXISTS(7017,"业务登记流水号为空"),
	BUSITYPE_ILLEGAL(7023,"非法的业务类型"),
	MEDICALSIGNINFO_NOTEXISTS(7024,"没有就医登记信息"),
	OPERATOR_ACCOUNT_EMPTY(7025, "经办人帐号不能为空"),
	HOSPITAL_SYSCODE_EMPTY(7026, "医院系统编号不能为空"),
	NOT_IN_HOSPITAL(7027, "没有入院数据，或不归当前经办人管"),
	CREATEMODEL_ONLYIN_HOSPITAL(7028, "只能在入院认证、开放抽查补采功能时才可以建模"),
	FETURE_UPLOAD_FAILED(7029, "上传模板失败"),
	BATCHID_NOT_EMPTY(7030, "批次号不能为空"),
	NOT_MEDICARE_OPER(7031, "非医保经办人不能登录"),
	NOT_HOSP_OPER(7032, "非医院经办人不能登录"),
	INVOKE_COMPLETE_RECOG_FAIL(7033, "调用完成认证失败"),
	ACCOUNT_HOSPITAL_NOT_MATCH(7055, "帐号与医院地址不匹配"),
	AA02_NOT_EXISTS(7055,"参保人系统关联表无医保记录"),
	IDENTIFY_RESULT_ILLEGAL(7056,"认证结果必须为 0:未处理、1:认证成功、2:认证失败 中的一个"),
	HOSPITALDEPT_SYSCODE_EMPTY(7057, "医院科室系统编号不能为空"),
	
	//资助类9XXX
	ZZPERSON_NOT_AUDIT(1201,"该学生信息还未审核"),
	ZZPERSON_NOT_EXISTS(1202,"该学生信息还未审核"),
	
	/***************************以上错误已经在使用，不要修改*************************/
	
	
	
	/****************************  第三方api接口相应错误码        开始*************************/
	PERSON_ADD_FAIL(9001,"人员信息已经存在"),
	SYSNO_NOT_EXIST(9002,"人员编号不能为空"),
	/****************************  第三方api接口相应错误码        结束*************************/
	
	/***************** 辅具领取相关错误信息 **********************/
	EMPTY_TOOL(8001,"没有同步工伤人员的辅具列表"),
	TOOL_INSTALL_FINISH(8002,"没有需要安装辅具列表"),
	NOT_INJURY_PERSON(8003,"不是工伤待遇人员"),
	NOT_INJURYTOOL_PERSON(8004,"不是工伤辅具人员"),
	INJURYTOOL_APPLY(8005,"已做过申请"),
	
	
	METHOD_IS_EMPTY(1001, "方法名为空或不存在"),
    APPKEY_IS_EMPTY(1002, "应用唯一key为空或不存在"),
    APPSECRET_IS_EMPTY(1003, "应用秘钥为空或不存在"),
    APP_CODE_IS_EMPTY(1004, "应用编码为空或不存在"),
    PARTNER_CODE_IS_EMPTY(1005, "用户ID为空或不存在"),
    SIGN_ERROR(1006, "签名错误"),
    APP_IS_NOT_OPEN_ERROR(1007, "应用已停用"),
    APP_TIME_OVER(1008, "应用已过期"),
    APP_CHANNEL_ERROR(1009, "渠道信息有误");
	
	
//	public static final int IDENTIFY_TIMES_LIMIT = 5;//认证超过当日限制
	
	//	public static final int MODEL_NOT_EXISTS = 7;//没有模板信息
	
	
//	public static final int MODEL_STATUS_ERROR = 18;//模板状态不正确
//	public static final int MODEL_PIC_NOT_EXISTS = 15;//没有模板照片信息
//	public static final int FEATURE_EXTRACT_FAIL = 17;//抽取特征失败

//	
//	public static final int MEDICAL_RECORD_NOT_EXISTS_IN_THIRD = 12;//没有就医记录
//	public static final int MEDICAL_STATUS_ERROR = 10;//就医状态不正确
//	public static final int MEDICAL_STATUS_DONE_ALREADY = 14;//已出院
//	public static final int MEDICAL_NOT_LOCAL_PATIENT = 16;//非本医院病人不允许登记
//	public static final int IDCARD_ILLEGLE = 19;//证件号码非法
//	public static final int AAC001_NOT_EXISTS = 13;//没有个人编号
//	AAC001_NOT_EXISTS(13,"没有个人编号"),
//	public static final int KE01_NOT_EXISTS = 21;//没有住院记录
//	KE01_NOT_EXISTS(21,"没有住院记录"),
//	public static final int ZE05_NOT_EXISTS = 22;//没有社保卡照片信息
//	ZE05_NOT_EXISTS(22,"没有社保卡照片信息"),
//	public static final int SEQ_MAPPER_NOTMAPPERED = 1010;//获取session失败 
//	SEQ_MAPPER_NOTMAPPERED(1010,"没有SEQ Mapper映射"),
	/***************************医保错误码添加，不要修改*************************/
	
	
	
	
 
	
	
	ErrorCodeConstants(){
		
	}
	
	ErrorCodeConstants(int val,String desc){
		this.desc = desc;
		this.val = val;
	}
	int val; 
	String desc;
	public int getVal() {
		return val;
	}
	public String getDesc() {
		//
		//boolean readDescFromDB = false;
		String tmpDesc = desc;
		/*if(readDescFromDB)
		{
		}*/
		return tmpDesc;
	}
	
}
