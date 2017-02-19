/**
 * Created by arthan on 15.02.2017. | Project brainburns
*/

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],

        browsers: ['Chrome'],

        files: [
            'app/first-test.spec.js',
            'app/app.component.spec.js'
        ],

        // Karma plugins loaded
        plugins: [
            'karma-jasmine',
            'karma-chrome-launcher'
        ],

        logLevel: config.LOG_DEBUG,

        singleRun: false
    });
};