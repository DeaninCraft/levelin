package com.deanin.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.apache.commons.lang3.StringUtils;

public class StringHelpers {
    public static String getBlockName(Block badBlockName) {
        return StringUtils.substringBetween(badBlockName.toString(),"{", "}");
    }
    public static int getBlockStateAge(BlockState blockState) {
        String age = StringUtils.substringBetween(blockState.toString(),"[", "]");
        return Integer.parseInt(String.valueOf(age.charAt(age.length()-1)));
    }
}
