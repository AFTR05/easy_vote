import 'package:flutter/material.dart';

class AuthLayout extends StatelessWidget {

  const AuthLayout({
    super.key
  });
  
  @override
  Widget build(BuildContext context) {

    final size = MediaQuery.of(context).size;
    final ScrollController _scrollController = ScrollController(initialScrollOffset: 0);

    return Scaffold(
      body: Scrollbar(
        controller: _scrollController,
        child: ListView(
          physics: const ClampingScrollPhysics(),
          children: <Widget>[
            ( size.width > 1000 )
              ? _DesktopBody( child: Container())
              : _MobileBody( child: Container() )
          ],
        ),
      )
    );
  }
}


class _DesktopBody extends StatelessWidget {

  final Widget child;

  const _DesktopBody({
    Key? key, 
    required this.child
  }) : super(key: key);



  @override
  Widget build(BuildContext context) {

    final size = MediaQuery.of(context).size;

    return Container(
     
    );
  }
}

class _MobileBody extends StatelessWidget {

  final Widget child;

  const _MobileBody({
    Key? key, 
    required this.child
  }) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Container(
      
          );
  }
}