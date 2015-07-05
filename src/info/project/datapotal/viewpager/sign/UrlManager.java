package info.project.datapotal.viewpager.sign;

public class UrlManager {
	
		// 슬라이딩 메뉴 URL 쿼리 집합
		private String mainUrl = "http://www.ibtk.kr/publicAssistanceFigurecover_api/";
		private String keyUrl = "8f297146209d85a9502285f56f33c3f9?";
		private String query = "model_query=";
		private String base = mainUrl+keyUrl+query;
		private String queryPage = "&model_query_pageable=";
		
		// 공공시설
		private String url0 = base+"%7B%22code%22:%7B%22$regex%22:%271.10%27%7D%7D"+queryPage+"%7B%27pageSize%27:86%7D";
		
		// 교통시설
		private String url1 = base+"%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%271.200%27%7D%7D," +
											      "%7B%22code%22:%7B%22$regex%22:%271.201%27%7D%7D," +
											      "%7B%22code%22:%7B%22$regex%22:%271.202%27%7D%7D%5D%7D"+
											      queryPage+"%7B%27pageSize%27:29%7D";
		
		// 상업시설
		private String url2 = base+"%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%271.300%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.301%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.302%27%7D%7D%5D%7D"+
														queryPage+"%7B%27pageSize%27:21%7D";
		
		// 관광, 문화시설
		private String url3 = base+"%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%271.400%27%7D%7D,"+
													"%7B%22code%22:%7B%22$regex%22:%271.401%27%7D%7D,"+
													"%7B%22code%22:%7B%22$regex%22:%271.402%27%7D%7D,"+
													"%7B%22code%22:%7B%22$regex%22:%271.403%27%7D%7D%5D%7D"
													+queryPage+"%7B%27pageSize%27:33%7D";
		
		// 체육 시설
		private String url4 = base+"%7B$or:%5B%7B%22code%22:%7B%22$regex%22:%271.500%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.501%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.502%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.503%27%7D%7D,"+
														"%7B%22code%22:%7B%22$regex%22:%271.504%27%7D%7D%5D%7D"
														+queryPage+"%7B%27pageSize%27:42%7D";
		
		// 안전 유도
		private String url5 = base+"%7B%22code%22:%7B%22$regex%22:%272.100%27%7D%7D";
		
		// 화재 비상
		private String url6 = base+"%7B%22code%22:%7B%22$regex%22:%272.200%27%7D%7D";
		
		// 금지
		private String url7 = base+"%7B%22code%22:%7B%22$regex%22:%272.30%27%7D%7D"
															+queryPage+"%7B%27pageSize%27:39%7D";
		
		// 경고 주의
		private String url8 = base+"%7B%22code%22:%7B%22$regex%22:%272.40%27%7D%7D"
															+queryPage+"%7B%27pageSize%27:27%7D";
		
		// 지시
		private String url9 = base+"%7B%22code%22:%7B%22$regex%22:%272.50%27%7D%7D"
															+queryPage+"%7B%27pageSize%27:15%7D";
		public String [] url = {url0,url1,url2,url3,url4,url5,url6,url7,url8,url9};
		
		public String[] getURLList()
		{
			return url;
		}

}
