package com.answerlyl.myfinance;

import org.junit.jupiter.api.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/2/28 8:47
 **/
public class TestTokenizerFont {

    @Test
    public void testFont(){
        //text 为你要搜索的内容

        try {
            String text = "我爱我家臭宝";
            Set<String> set = new HashSet<>();

            StringReader re = new StringReader(text.trim());

            IKSegmenter ik= new IKSegmenter(re,true);

            Lexeme lex;

            while((lex = ik.next())!=null){

                set.add(lex.getLexemeText());

                System.out.println(lex.getLexemeText());
            }




        }catch (Exception e){



    }
}

}
