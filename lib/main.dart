

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main(){
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
   
  const MyApp({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return const  MaterialApp(
      home: HomePage(), 
    ); 
  }
}


class HomePage extends StatefulWidget {
   
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
    static const platform =  MethodChannel('sampleProject');
    String result="";
      Future<void> getImei() async {
        try{
          final String data = await platform.invokeMethod('getImei');
          result = data;
        } on PlatformException catch(e){
          print('❌error al obtener el imei ');
        }
        setState(() {});
      }
  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      body:  Center(
         child: Text(result),
      ),
      floatingActionButton: FloatingActionButton(
        child: const Center(child:  Text('get Imei')),
        onPressed: () async {
           getImei();
        }
      ),
    );
  }
}
