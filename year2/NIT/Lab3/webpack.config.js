const path = require('path');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
    entry: './src/index.js',
    output: {
        path: path.resolve(__dirname, 'docs'),
        filename: 'js/main.js',
    },
      module: {
        rules: [
            {
                test: /\.m?js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env'],
                    },
                },
            },
     {
            test: /\.(png|jp(e*)g|svg)$/, 
        
            use: [{
             
                loader: 'url-loader',
                options: { 
                    
                      publicPath: '../',
                            useRelativePaths: true,
                    limit: 8000, // Convert images < 8kb to base64 strings
                    name: 'css/images/[hash]-[name].[ext]'
                } 
            }]
        },
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader',
                ],
            },



            // ...additional rules...
        ],
},

 plugins: [
        new MiniCssExtractPlugin({
            filename: "css/[name].css",
        }),
    ],

};