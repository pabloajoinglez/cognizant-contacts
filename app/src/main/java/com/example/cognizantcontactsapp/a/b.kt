package com.example.cognizantcontactsapp.a

class b {

    companion object {

        fun c(plaintext : String) : String{
            val key = "4456"
            //To char[]
            //To char[]
            val char_plaintext : CharArray = plaintext.toCharArray()
            val char_key : CharArray= key.toCharArray()

            //Xor

            //Xor
            var i: Int
            val stringBuilder: StringBuilder = StringBuilder()

            i = 0
            while (i < char_plaintext.size) {
                stringBuilder.append( (char_plaintext[i].toInt() xor char_key[i % char_key.size].toInt()).toChar())
                i++
            }


            return stringBuilder.toString()
        }
    }

}