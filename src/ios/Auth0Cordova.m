#import <Cordova/CDV.h>
#import <Auth0/Auth0.h>

@interface Auth0Cordova : CDVPlugin
- (void)login:(CDVInvokedUrlCommand*)command;
@end

@implementation Auth0Cordova

- (void)login:(CDVInvokedUrlCommand*)command {
    A0WebAuth *auth = [[A0WebAuth alloc] init];
    [auth startWithCompletion:^(A0Credentials * _Nullable credentials, NSError * _Nullable error) {
        if (error == nil) {
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:credentials.accessToken];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        } else {
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.localizedDescription];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

@end
