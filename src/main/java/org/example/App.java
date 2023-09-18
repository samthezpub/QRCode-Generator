package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Size size = new Size("1000x1000");
        System.out.println(size.getVertical());
        System.out.println(size.getHorizontal());
        System.out.println( "" );
        URL url = null;

        try {
            url = new URL("https://api.qrserver.com/v1/create-qr-code/?size="+ size.toString() +"&data=Example");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Получаем InputStream для чтения данных из URL
                InputStream inputStream = con.getInputStream();

                // Создаем файл, в который будем сохранять изображение
                File imageFile = new File("qr_code.png");

                // Создаем FileOutputStream для записи данных в файл
                FileOutputStream outputStream = new FileOutputStream(imageFile);

                // Читаем данные из InputStream и записываем их в FileOutputStream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Закрываем потоки
                inputStream.close();
                outputStream.close();

                System.out.println("Изображение успешно сохранено в файл: " + imageFile.getAbsolutePath());
            } else {
                System.out.println("Ошибка: HTTP-код ответа " + responseCode);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
