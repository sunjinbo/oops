#extension GL_OES_EGL_image_external : require
//create file by ja333son 2018/2/28

precision mediump float;

varying vec2 vTexCoord;

uniform samplerExternalOES sTexture;

void main() {

    vec2 newCoord_lt = vec2(vTexCoord.x * 3.0, vTexCoord.y * 3.0);
    vec2 newCoord_mt = vec2((vTexCoord.x * 3.0 - 1.0), vTexCoord.y * 3.0);
    vec2 newCoord_rt = vec2((vTexCoord.x * 3.0 - 2.0), vTexCoord.y * 3.0);

    vec2 newCoord_lm = vec2(vTexCoord.x * 3.0, vTexCoord.y * 3.0 - 1.0);
    vec2 newCoord_mm = vec2((vTexCoord.x * 3.0 - 1.0), vTexCoord.y * 3.0 - 1.0);
    vec2 newCoord_rm = vec2((vTexCoord.x * 3.0 - 2.0), vTexCoord.y * 3.0 - 1.0);

    vec2 newCoord_lb = vec2(vTexCoord.x * 3.0, vTexCoord.y * 3.0 - 2.0);
    vec2 newCoord_mb = vec2((vTexCoord.x * 3.0 - 1.0), vTexCoord.y * 3.0 - 2.0);
    vec2 newCoord_rb = vec2((vTexCoord.x * 3.0 - 2.0), vTexCoord.y * 3.0 - 2.0);



    vec4 centralColor_lt = texture2D(sTexture, newCoord_lt);
    vec4 centralColor_mt = texture2D(sTexture, newCoord_mt);
    vec4 centralColor_rt = texture2D(sTexture, newCoord_rt);

    vec4 centralColor_lm = texture2D(sTexture, newCoord_lm);
    vec4 centralColor_mm = texture2D(sTexture, newCoord_mm);
    vec4 centralColor_rm = texture2D(sTexture, newCoord_rm);

    vec4 centralColor_lb = texture2D(sTexture, newCoord_lb);
    vec4 centralColor_mb = texture2D(sTexture, newCoord_mb);
    vec4 centralColor_rb = texture2D(sTexture, newCoord_rb);



    if(vTexCoord.x * 3.0 < 1.0 && vTexCoord.x * 3.0 > 0.0 && vTexCoord.y * 3.0 < 1.0 && vTexCoord.y * 3.0 > 0.0) {
        gl_FragColor = centralColor_lt;
    } else if (vTexCoord.x * 3.0 - 1.0 < 1.0 && vTexCoord.x * 3.0 - 1.0 > 0.0 && vTexCoord.y * 3.0 < 1.0 && vTexCoord.y * 3.0 > 0.0) {
        gl_FragColor = centralColor_mt;
    } else if (vTexCoord.x * 3.0 - 2.0 < 1.0 && vTexCoord.x * 3.0 - 2.0 > 0.0 && vTexCoord.y * 3.0 < 1.0 && vTexCoord.y * 3.0 > 0.0) {
        gl_FragColor = centralColor_rt;
    } else if (vTexCoord.x * 3.0 < 1.0 && vTexCoord.x * 3.0 > 0.0 && vTexCoord.y * 3.0 - 1.0 < 1.0 && vTexCoord.y * 3.0 - 1.0 > 0.0) {
        gl_FragColor = centralColor_lm;
    } else if (vTexCoord.x * 3.0 - 1.0 < 1.0 && vTexCoord.x * 3.0 - 1.0 > 0.0 && vTexCoord.y * 3.0 - 1.0 < 1.0 && vTexCoord.y * 3.0 - 1.0 > 0.0) {
        gl_FragColor = centralColor_mm;
    } else if (vTexCoord.x * 3.0 - 2.0 < 1.0 && vTexCoord.x * 3.0 - 2.0 > 0.0 && vTexCoord.y * 3.0 - 1.0 < 1.0 && vTexCoord.y * 3.0 - 1.0 > 0.0) {
        gl_FragColor = centralColor_rm;
    } else if (vTexCoord.x * 3.0 < 1.0 && vTexCoord.x * 3.0 > 0.0 && vTexCoord.y * 3.0 - 2.0 < 1.0 && vTexCoord.y * 3.0 - 2.0 > 0.0) {
        gl_FragColor = centralColor_lb;
    } else if (vTexCoord.x * 3.0 - 1.0 < 1.0 && vTexCoord.x * 3.0 - 1.0 > 0.0 && vTexCoord.y * 3.0 - 2.0 < 1.0 && vTexCoord.y * 3.0 - 2.0 > 0.0) {
        gl_FragColor = centralColor_mb;
    } else if (vTexCoord.x * 3.0 - 2.0 < 1.0 && vTexCoord.x * 3.0 - 2.0 > 0.0 && vTexCoord.y * 3.0 - 2.0 < 1.0 && vTexCoord.y * 3.0 - 2.0 > 0.0) {
        gl_FragColor = centralColor_rb;
    }
}