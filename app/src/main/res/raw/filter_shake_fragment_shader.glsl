#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/3/2

precision highp float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;
uniform float fit_time;

void main() {
    float flag = fit_time + 1.0;
    float trans = (flag - 1.0) / 2.0;
    float scale = 1.0 / flag;
    vec2 transformPos = vTexCoord + vec2(trans);
    vec4 srcColor = texture2D(sTexture, transformPos * (scale));
    vec4 srcColorR = texture2D(sTexture, transformPos * (scale) + fit_time * 0.05);
    vec4 srcColorG = texture2D(sTexture, transformPos * (scale));
    vec4 srcColorB = texture2D(sTexture, transformPos * (scale) - fit_time * 0.05);
    vec4 final_Color = vec4(0.0, 0.0, srcColorB.b, 1.0) + vec4(srcColorR.r, 0.0, 0.0, 1.0) + vec4(0.0, srcColorG.g, 0.0, 1.0);
    gl_FragColor = final_Color;
}