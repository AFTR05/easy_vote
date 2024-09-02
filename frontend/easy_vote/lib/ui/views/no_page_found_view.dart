
import 'package:easy_vote/ui/labels/custom_labels.dart';
import 'package:flutter/material.dart';

class NoPageFoundView extends StatelessWidget {
  const NoPageFoundView({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 20),
      width: size.width,
      child: Center(
        child: FittedBox(
          child: RichText(
              text: TextSpan(
                children: <TextSpan>[
                  TextSpan(
                    text: '404',
                    style: CustomLabels.noPageFound,
                  ),
                  TextSpan(
                    text: ' - ',
                    style: CustomLabels.noPageFoundGreen,
                  ),
                  TextSpan(
                    text: 'no page found',
                    style: CustomLabels.noPageFound,
                  ),
                ],
              )
            ),
        ),
      ),
    );
  }
}