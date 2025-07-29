package com.ds04011.widbuddy.common;


public class HtmlUtil {

	
	public static String extractPlainText(String html) {
        if (html == null || html.isEmpty()) return "";

        // 이미지 태그 제거
        String result = html.replaceAll("<img[^>]*>", "");

        // <br>, <div>, <p> 등의 태그 제거
        result = result.replaceAll("<[^>]+>", "");

        // 특수 HTML 엔티티 → 일반 문자로 변환 (&nbsp;, &lt;, &gt; 등)
        result = result.replace("&nbsp;", " ");
        result = result.replace("&lt;", "<");
        result = result.replace("&gt;", ">");
        result = result.replace("&amp;", "&");

        // 여러 줄바꿈 및 공백 정리
        result = result.replaceAll("\\s{2,}", " ");
        result = result.replaceAll("\\n{2,}", "\n");

        return result.trim();
    }


}
