/**
 *
 */
package coya.dto;

/**
 * @author Raksha
 *
 */
public class ExceptionResponseDto {
	 public ExceptionResponseDto(String detail, Integer status) {
			super();
			this.detail = detail;
			this.status = status;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		private String detail;
	    private Integer status;

}
