@import AppKit;
@import Foundation;
@import ObjectiveC;

#include <jni.h>

@interface AWTWindow : NSObject <NSWindowDelegate>

@property (nonatomic, retain) NSWindow *nsWindow;

@end

@interface AWTWindow (EnableColorManagement)

@end

@implementation AWTWindow (EnableColorManagement)

- (void)ecm_setNsWindow:(NSWindow *)nsWindow
{
    [self ecm_setNsWindow:nsWindow];
    [nsWindow setColorSpace:[NSColorSpace genericRGBColorSpace]];
}

@end

static void objc_exchangeInstanceMethodImplementations(const char* className, SEL originlSelector, SEL replacementSelector)
{
    Class class = objc_getClass(className);
    Method originalMethod = class_getInstanceMethod(class, originlSelector);
    Method replacementMethod = class_getInstanceMethod(class, replacementSelector);
    method_exchangeImplementations(originalMethod, replacementMethod);
}

JNIEXPORT void JNICALL Java_at_niw_EnableColorManagement_ApplicationLoadListener_load(JNIEnv *env, jobject this)
{
    objc_exchangeInstanceMethodImplementations("AWTWindow", @selector(setNsWindow:), @selector(ecm_setNsWindow:));
    NSLog(@"Loaded EnableColorManagement");
}
