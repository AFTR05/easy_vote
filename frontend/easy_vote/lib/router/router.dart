import 'package:easy_vote/router/no_page_found_handlers.dart';
import 'package:fluro/fluro.dart';

class Flurorouter {
  static final FluroRouter router = FluroRouter();

  static String rootRoute = '/';
  static String loginRoute = '/auth/sign_in';
  static String signUpRoute = '/auth/sign_up';

  //Dashboard
  static String dashboardRoute = '/dashboard';
  static String rewardsRoute = '/dashboard/rewards';
  static String myProductsRoute = '/dashboard/my_products';
  static String myPublicationsRoute = '/dashboard/my_publications';  
  static String myPublicationRoute = '/dashboard/my_publication/:id';
  static String myTransfersRoute = '/dashboard/my_transfers';
  static String myTransferRoute = '/dashboard/my_transfer/:id';
  static String myOffersRoute = '/dashboard/my_offers';
  static String myRewardsRoute = '/dashboard/my_rewards';
  static String complaintsRoute = '/dashboard/complaints';
  static String institutionalBlogRoute = '/dashboard/institutional_blog';
  static String profileRoute = '/dashboard/profile';

  static void configureRoutes(){
    // Auth Handler
    router.define(rootRoute, handler: NoPageFoundHandlers.noPageFound,transitionType: TransitionType.none);
    
    //no page found
    router.notFoundHandler = NoPageFoundHandlers.noPageFound;
  }

}