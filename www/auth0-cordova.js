var exec = require('cordova/exec');

exports.login = function (success, error) {
    exec(success, error, 'Auth0Cordova', 'login', []);
};
