package csu.web.mypetstore.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class GetCaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 120;
        int height = 30;
        int codeCount = 6;
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 设置字体
        Font font = new Font("Fixedsys", Font.BOLD, 20);
        g.setFont(font);

        Random random = new Random();

        // 添加干扰线
        for (int i = 0; i < 20; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            g.setColor(getRandomColor(160, 200));
            g.drawLine(xs, ys, xe, ye);
        }

        String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder captchaCode = new StringBuilder();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(code.charAt(random.nextInt(code.length())));
            g.setColor(getRandomColor(80, 160));
            g.drawString(strRand, 15 * i + 6, 24);
            captchaCode.append(strRand);
        }

        g.dispose();

        // 将验证码保存到session中
        request.getSession().setAttribute("captcha", captchaCode.toString());

        // 输出图片
        OutputStream out = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", out);
        out.close();
    }

    private Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}