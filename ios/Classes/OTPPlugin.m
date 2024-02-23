#import "OTPPlugin.h"
#if __has_include(<otp_autofill_plus/otp_autofill_plus-Swift.h>)
#import <otp_autofill_plus/otp_autofill_plus-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "otp_autofill_plus-Swift.h"
#endif

@implementation OTPPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftOTPPlugin registerWithRegistrar:registrar];
}
@end
