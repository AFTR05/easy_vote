import 'package:flutter/material.dart';

class MainPageLayout extends StatelessWidget {
  final Widget child;

  const MainPageLayout({super.key, required this.child});

  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Expanded(
            child: Scrollbar(
              child: ListView(
                physics: const ClampingScrollPhysics(),
                children: [
                  child,
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
