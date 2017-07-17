package com.gtranslate.parsing;

import com.google.gson.Gson;
import com.gtranslate.URLCONSTANTS;
import com.gtranslate.text.Text;
import com.gtranslate.text.TextTranslate;
import com.gtranslate.utils.WebUtils;

import java.util.List;
import java.util.Map;

public class ParseTextTranslate implements Parse {
    private TextTranslate textTranslate;
    private StringBuilder url;
    private Gson gson;

    public ParseTextTranslate(TextTranslate textTranslate) {
        this.textTranslate = textTranslate;
        this.gson = new Gson();
    }

    public void parse() {
        appendURL();
        String result = WebUtils.source(url.toString());
System.out.println("Result::"+result);
        Map map = gson.fromJson(result, Map.class);
        String translation = (String) ((Map) ((List) map.get("sentences")).get(0)).get("trans");

        Text output = textTranslate.getOutput();
        output.setText(translation);
    }

    public TextTranslate getTextTranslate() {
        return textTranslate;
    }

    public void appendURL() {
        Text input = textTranslate.getInput();
        Text output = textTranslate.getOutput();
        url = new StringBuilder(URLCONSTANTS.GOOGLE_TRANSLATE_TEXT);
        url.append("text=" + input.getText().replace(" ", "%20"));
        url.append("&oe=UTF-8");
        url.append("&tl=" + output.getLanguage());
        url.append("&client=z");
        url.append("&sl=" + input.getLanguage());
        /*url.append("client=t&sl=auto&tl="+ output.getLanguage()
        +"&hl=" + input.getLanguage()
        +"&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&otf=1&rom=1&ssel=0&tsel=3&kc=1&tk=620730|996163"
        + "&q=" + input.getText().replace(" ", "%20"));*/

    }
}
