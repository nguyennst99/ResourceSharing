/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.utils;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author nguyennst
 */
public class RandomCode implements Serializable {

    static Random rd = new Random();

    public static String getRandomCode() {
        String rdCode = "";
        for (int i = 0; i < 6; i++) {
            int temp = rd.nextInt(10);
            rdCode += temp;
        }
        return rdCode;
    }
}
