package com.llw.hospital.dto;

public class SysOrganizationTreeDto extends SysOrganizationDto {
	 private Integer childCount;

	    //顶级父类的orgCategory
	    private Integer parentTopOrgCategory;
	    
	    private String pname;
	    
	    private Integer type;
	    
	    private String deviceCode;
	    
	    private String cameraIndexCode;
	    
	    private String rtspUrl;
	    
	    private String deviceNetStatus;
	    

		public String getDeviceNetStatus() {
			return deviceNetStatus;
		}

		public void setDeviceNetStatus(String deviceNetStatus) {
			this.deviceNetStatus = deviceNetStatus;
		}

		public String getDeviceCode() {
			return deviceCode;
		}

		public void setDeviceCode(String deviceCode) {
			this.deviceCode = deviceCode;
		}

		public String getCameraIndexCode() {
			return cameraIndexCode;
		}

		public void setCameraIndexCode(String cameraIndexCode) {
			this.cameraIndexCode = cameraIndexCode;
		}

		public String getRtspUrl() {
			return rtspUrl;
		}

		public void setRtspUrl(String rtspUrl) {
			this.rtspUrl = rtspUrl;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getPname() {
			return pname;
		}

		public void setPname(String pname) {
			this.pname = pname;
		}

		public Integer getChildCount() {
	        return childCount;
	    }

	    public void setChildCount(Integer childCount) {
	        this.childCount = childCount;
	    }

		public Integer getParentTopOrgCategory() {
			return parentTopOrgCategory;
		}

		public void setParentTopOrgCategory(Integer parentTopOrgCategory) {
			this.parentTopOrgCategory = parentTopOrgCategory;
		}
	    
}
