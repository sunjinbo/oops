#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision mediump float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;

void main() {
    vec2 newCoord_lt = vec2(vTexCoord.x * 2.0, vTexCoord.y * 2.0);
    vec2 newCoord_rt = vec2(1.0 - (vTexCoord.x * 2.0 - 1.0), vTexCoord.y * 2.0);
    vec2 newCoord_lb = vec2(vTexCoord.x * 2.0, 1.0 - (vTexCoord.y * 2.0 - 1.0));
    vec2 newCoord_rb = vec2(1.0 - (vTexCoord.x * 2.0 - 1.0), 1.0 - (vTexCoord.y * 2.0 - 1.0));
    vec4 centralColor_lt = texture2D(sTexture, newCoord_lt);
    vec4 centralColor_rt = texture2D(sTexture, newCoord_rt);
    vec4 centralColor_lb = texture2D(sTexture, newCoord_lb);
    vec4 centralColor_rb = texture2D(sTexture, newCoord_rb);
    if(vTexCoord.x * 2.0 < 1.0 && vTexCoord.x * 2.0 > 0.0 && vTexCoord.y * 2.0 < 1.0 && vTexCoord.y * 2.0 > 0.0) {
        gl_FragColor = centralColor_lt;
    } else if (vTexCoord.x * 2.0 - 1.0 < 1.0 && vTexCoord.x * 2.0 - 1.0 > 0.0 && vTexCoord.y * 2.0 < 1.0 && vTexCoord.y * 2.0 > 0.0) {
        gl_FragColor = centralColor_rt;
    } else if (vTexCoord.x * 2.0 < 1.0 && vTexCoord.x * 2.0 > 0.0 && vTexCoord.y * 2.0 - 1.0 < 1.0 && vTexCoord.y * 2.0 - 1.0 > 0.0) {
        gl_FragColor = centralColor_lb;
    } else if (vTexCoord.x * 2.0 - 1.0 < 1.0 && vTexCoord.x * 2.0 - 1.0 > 0.0 && vTexCoord.y * 2.0 - 1.0 < 1.0 && vTexCoord.y * 2.0 - 1.0 > 0.0) {
        gl_FragColor = centralColor_rb;
    }
}