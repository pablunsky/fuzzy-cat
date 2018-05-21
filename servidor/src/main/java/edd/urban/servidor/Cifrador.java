/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.urban.servidor;

/**
 *
 * @author pablunsky
 */
public class Cifrador {
    
    static String clave = "G@t&T0B0n&t0031416SuC3l&T3aM4";
    
    public Cifrador(){}
    
    public static String cifrar(String s){
        String c = "";
        char[] ba = s.toCharArray();
        int k = 0;
        for(int i = 0; i < ba.length; i++){
            if(k == clave.length())
                k = 0;
            
            String bits = Integer.toBinaryString(ba[i]);
            String trans = Integer.toBinaryString(clave.charAt(k));
            
            String word = "";
            
            if(bits.length() > trans.length()){
                int p = trans.length()-1;
                for(int j = bits.length()-1; j >= 0; j--){
                    char c1 = bits.charAt(j);
                    char c2;
                    if(p >= 0)
                        c2 = trans.charAt(p);
                    else
                        c2 = '0';
                    
                    boolean b1 = (c1 == '1'); 
                    boolean b2 = (c2 == '1');

                    if(b1 ^ b2)
                        word = "1" + word;
                    else
                        word = "0"+ word;
                    p--;
                }
            }
            else if(bits.length() < trans.length()){
                int p = bits.length()-1;
                for(int j = trans.length()-1; j >= 0; j--){
                    char c2 = trans.charAt(j);
                    char c1;
                    if(p >= 0)
                        c1 = bits.charAt(p);
                    else
                        c1 = '0';
                    
                    boolean b1 = (c1 == '1'); 
                    boolean b2 = (c2 == '1');

                    if(b1 ^ b2)
                        word = "1"+ word;
                    else
                        word = "0"+ word;
                    p--;
                }
            }
            else{
                for(int j = trans.length()-1; j >= 0; j--){
                    
                    char c1 = bits.charAt(j);
                    char c2 = trans.charAt(j);
                    
                    boolean b1 = (c1 == '1'); 
                    boolean b2 = (c2 == '1');

                    if(b1 ^ b2)
                        word = "1"+ word;
                    else
                        word = "0"+ word;
                }
            }
            c += (char)Integer.parseInt(word,2);
            k++;
        }
        
        return c;
    }
}
