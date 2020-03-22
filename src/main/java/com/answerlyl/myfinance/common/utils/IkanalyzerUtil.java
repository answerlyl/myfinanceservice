package com.answerlyl.myfinance.common.utils;

import com.answerlyl.myfinance.entity.Ikanalyzer;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/2/28 9:32
 **/
public class IkanalyzerUtil {

    public static  List<Ikanalyzer> cutWord(String word, List<Ikanalyzer> list){

        try{
            StringReader re = new StringReader(word.trim());

            IKSegmenter ik= new IKSegmenter(re,true);

            Lexeme lex;

            while((lex = ik.next())!=null){
                Ikanalyzer ikanalyzer = new Ikanalyzer();
                ikanalyzer.setWord(lex.getLexemeText());
                list.add(ikanalyzer);
            }

        }catch (Exception e){

        }

        return list;
    }
}
