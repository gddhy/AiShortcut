package com.miui.AiShortcut;

import android.util.*;
import java.io.*;
import android.content.*;
import android.widget.*;

public class cmdUtil
{
	public static void openMiAiShortcut(Context context){
		if (aiInfo.hasMiAi(context)) {
			runRootShell(new String[]{"am start -n com.miui.voiceassist/com.xiaomi.voiceassistant.AiSettings.AiShortcutActivity"});
		} else {
			Toast.makeText(context,"请安装或升级小爱",Toast.LENGTH_LONG).show();
		}
	}
	
	private static String runRootShell(String[] cmds){    

        String result = null;
        int ret = -1;
        java.lang.Process process;    
        try {
            process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            for(int i=0; i< cmds.length; i++) {
                os.writeBytes(cmds[i] + "\n");
            }
            os.writeBytes("exit\n");
            os.flush();
            try {
                ret = process.waitFor();
                Log.d("lx", "ret= " + ret);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            os.close();

            ByteArrayOutputStream resultOutStream = new ByteArrayOutputStream();
            InputStream errorInStream = new BufferedInputStream(process.getErrorStream());
            InputStream processInStream = new BufferedInputStream(process.getInputStream());
            int num = 0;

            byte[] bs = new byte[1024];

            while((num=errorInStream.read(bs))!=-1){

                resultOutStream.write(bs,0,num);

            }

            while((num=processInStream.read(bs))!=-1){

                resultOutStream.write(bs,0,num);

			}

            result=new String(resultOutStream.toByteArray());

            //println( "result: " + result);

            errorInStream.close();
            errorInStream=null;

            processInStream.close();
            processInStream=null;

            resultOutStream.close();
            resultOutStream=null;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }


		result =  "result code: " + ret + " details: " +result;
        return result;    
    } 
	
}
