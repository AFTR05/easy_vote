import 'package:easy_vote/router/router.dart';
import 'package:easy_vote/services/local_storage.dart';
import 'package:easy_vote/services/navigation_service.dart';
import 'package:easy_vote/services/notifications_service.dart';
import 'package:flutter/material.dart';



void main() async {
  await LocalStorage.configurePrefs();
  Flurorouter.configureRoutes();
  runApp(const AppState());
}



class AppState extends StatelessWidget {
  const AppState({super.key});

  @override
  Widget build(BuildContext context) {
    return const MyApp();
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Easy Vote',

      initialRoute: '/',
      onGenerateRoute: Flurorouter.router.generator,
      navigatorKey: NavigationService.navigatorKey,
      scaffoldMessengerKey: NotificationsService.messengerKey,
      theme: ThemeData.dark().copyWith(
        scaffoldBackgroundColor: const Color.fromRGBO(16, 16, 16,1),
        scrollbarTheme: const ScrollbarThemeData().copyWith(
          thumbColor: MaterialStateProperty.all(
            Colors.grey
          )
        ),
        primaryColor: const Color.fromRGBO(0, 181, 60, 1),
      )
    );
  }
}