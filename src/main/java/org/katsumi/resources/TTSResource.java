package org.katsumi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Google翻訳サービスクラス
 *
 * @author Katsumi
 * @since May 7, 2015
 */
@Path("/tts")
public class TTSResource
{
    /**
     * 入力された英単語の音声ファイルをダウンロードします。
     *
     * @param text 英単語
     * @return 英単語の音声ファイル
     * @throws IOException Google翻訳への接続に失敗した場合
     */
    @GET
    @Produces("audio/mpeg")
    public Response textToSpeech(@QueryParam("text") String text) throws IOException
    {
        final URL url = new URL("http://translate.google.com/translate_tts?tl=en&q=" + text);
        final URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla");
        return Response.ok(connection.getInputStream()).build();
    }
}
