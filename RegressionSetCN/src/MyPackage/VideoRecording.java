package MyPackage;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import java.awt.*;

public class VideoRecording {
	private static ScreenRecorder screenRecorder;
	public static void startRecording() throws Exception
	{
	GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	screenRecorder = new ScreenRecorder(gc,
	new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,DepthKey, 24, FrameRateKey, Rational.valueOf(15),QualityKey, 1.0f,KeyFrameIntervalKey, 15 * 60),new Format(MediaTypeKey,MediaType.VIDEO, EncodingKey, "black",FrameRateKey, Rational.valueOf(30)),null);
	screenRecorder.start();
	}
	public static void stopRecording() throws Exception
	{
	screenRecorder.stop();
	}
	}