import 'package:easy_vote/shared/widgets/custom_logo.dart';
import 'package:easy_vote/ui/views/no_page_found_view.dart';
import 'package:flutter/material.dart';

class NoPageFoundLayout extends StatelessWidget {
  const NoPageFoundLayout({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Expanded(
            child: Scrollbar(
              child: ListView(
                physics: const ClampingScrollPhysics(),
                children: const [
                  _Body(child: NoPageFoundView())
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
  
}


class _Body extends StatelessWidget {
  final Widget child;
  const _Body({
    required this.child
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return SizedBox(
      width: size.width,
      height: size.height,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox( height: 20 ),
          const FittedBox(child: CustomLogo()),
          const SizedBox( height:  200 ),
          child,
        ],
      ),
    );
  }
}