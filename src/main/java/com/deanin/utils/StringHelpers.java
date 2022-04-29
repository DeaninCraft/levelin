package com.deanin.utils;

import com.deanin.levelin.Levelin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelpers {
    public static String getBlockName(Block badBlockName) {
        return StringUtils.substringBetween(badBlockName.toString(),"{", "}");
    }
    public static int getBlockStateAge(BlockState blockState) {
        Pattern ageRegex = Pattern.compile("age=\\d");
        Matcher matcher = ageRegex.matcher(blockState.toString());
        if(matcher.find()) {
            return Integer.parseInt(String.valueOf(matcher.group().charAt(matcher.group().length() - 1)));
        }
        return 0;
    }
}
