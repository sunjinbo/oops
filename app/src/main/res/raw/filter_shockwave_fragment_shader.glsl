#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision highp float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;
uniform float fit_time;

void main() {
    float flag = fit_time + 1.0;
    float trans = (flag - 1.0) / 2.0;
    float scale = 1.0 / flag;
    vec2 transformPos = vTexCoord + vec2(trans);
    vec4 transScaleColor = texture2D(sTexture, transformPos * (scale));
    gl_FragColor = mix(texture2D(sTexture, vTexCoord), transScaleColor, 0.2 * ((1.0 - fit_time) * (1.0 - fit_time)));
}