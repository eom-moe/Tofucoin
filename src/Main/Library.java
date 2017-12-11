package Main;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.omg.CORBA_2_3.portable.InputStream;

public class Library {
	static byte[] getByteObject(Object data) {
		byte[] retObject = null;
		try {
			ByteArrayOutputStream byteos = new ByteArrayOutputStream();
			ObjectOutputStream objos = new ObjectOutputStream(byteos);
			objos.writeObject(data);
			objos.close();
			byteos.close();
			retObject = byteos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retObject;
	}

	static boolean isDirectory(String dirPath) {
		return (new File(dirPath)).isDirectory();
	}

	static void fileWrite(String path, String content) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.isFile() && file.canWrite()) {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
				pw.print(content);
				pw.close();
			} else {
				Log.log("cannot write file", Constant.Log.IMPORTANT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.log("cannot write file", Constant.Log.IMPORTANT);
		}
	}

	static void fileWrite(String path, byte[] data) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			Log.log("cannot write file", Constant.Log.IMPORTANT);
		}
	}

	static byte[] readFileToByte(String path) throws Exception {
		byte[] b = new byte[1];
		FileInputStream fis = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (fis.read(b) > 0) {
			baos.write(b);
		}
		baos.close();
		fis.close();
		b = baos.toByteArray();

		return b;
	}

	static Object createSendMessageObject(byte[] objByte) {
		Object obj = null;
		try {
			ByteArrayInputStream byteis = new ByteArrayInputStream(objByte);
			ObjectInputStream objis = new ObjectInputStream(byteis);
			obj = objis.readObject();
			byteis.close();
			objis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return obj;
	}

}