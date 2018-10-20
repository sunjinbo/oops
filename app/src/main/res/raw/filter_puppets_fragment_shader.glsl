//create file by ja333son 2018/3/6

precision mediump float;

uniform sampler2D srcTexture;
uniform sampler2D cacheTexture;
uniform float fit_time;
varying vec2 vTexCoord;
void main() {

    if(vTexCoord.x > (1.6 *fit_time) - 0.6 && vTexCoord.x < (1.6 *fit_time)) {
        gl_FragColor = texture2D(srcTexture, vTexCoord);
    } else {
        float block = 150.0;
        float delta = 1.0/block;
        vec4 color = vec4(0.0);

        float factor[9];
        factor[0] = 0.0947416; factor[1] = 0.118318; factor[2] = 0.0947416;
        factor[3] = 0.118318; factor[4] = 0.147761; factor[5] = 0.118318;
        factor[6] = 0.0947416; factor[7] = 0.118318; factor[8] = 0.0947416;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                float x = max(0.0, vTexCoord.x + float(i) * delta);
                float y = max(0.0, vTexCoord.y + float(i) * delta);
                color += texture2D(cacheTexture, vec2(x, y)) * factor[(i+1)*3+(j+1)];
            }
        }

        vec4 blurOutput = vec4(vec3(color), 1.0);
        float gray = (blurOutput.r + blurOutput.g + blurOutput.b) / 3.0;
        vec3 grayscale = vec3(gray);
        gl_FragColor = vec4(grayscale, 1.0);
    }
}