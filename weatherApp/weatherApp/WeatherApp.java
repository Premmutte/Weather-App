����   >1
      java/lang/Object <init> ()V
  	 
   
WeatherApp getLocationData /(Ljava/lang/String;)Lorg/json/simple/JSONArray;
      org/json/simple/JSONArray get (I)Ljava/lang/Object;  org/json/simple/JSONObject  latitude
     &(Ljava/lang/Object;)Ljava/lang/Object;  java/lang/Double
     doubleValue ()D ! 	longitude   # $ % makeConcatWithConstants (DD)Ljava/lang/String;
  ' ( ) fetchApiResponse 0(Ljava/lang/String;)Ljava/net/HttpURLConnection;
 + , - . / java/net/HttpURLConnection getResponseCode ()I	 1 2 3 4 5 java/lang/System out Ljava/io/PrintStream; 7 Error: Could not connect to API
 9 : ; < = java/io/PrintStream println (Ljava/lang/String;)V ? java/lang/StringBuilder
 >  B java/util/Scanner
 + D E F getInputStream ()Ljava/io/InputStream;
 A H  I (Ljava/io/InputStream;)V
 A K L M hasNext ()Z
 A O P Q nextLine ()Ljava/lang/String;
 > S T U append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
 A W X  close
 + Z [  
disconnect ] !org/json/simple/parser/JSONParser
 \ 
 ` a b c d java/lang/String valueOf &(Ljava/lang/Object;)Ljava/lang/String;
 \ f g h parse &(Ljava/lang/String;)Ljava/lang/Object; j hourly l time
  n o p findIndexOfCurrentTime (Lorg/json/simple/JSONArray;)I r temperature_2m t weathercode v java/lang/Long
 u x y z 	longValue ()J
  | } ~ convertWeatherCode (J)Ljava/lang/String; � relativehumidity_2m � windspeed_10m
   � temperature
  � c � (D)Ljava/lang/Double;
  � � � put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; � weather_condition � humidity
 u � c � (J)Ljava/lang/Long; � 	windspeed � java/lang/Exception
 � � �  printStackTrace �   � +
 ` � � � 
replaceAll 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;  � $ � &(Ljava/lang/String;)Ljava/lang/String; � results � java/net/URL
 � �  =
 � � � � openConnection ()Ljava/net/URLConnection; � GET
 + � � = setRequestMethod
 + � �  connect � java/io/IOException
 � �
  � � Q getCurrentTime
  � � / size
 ` � � � equalsIgnoreCase (Ljava/lang/String;)Z
 � � � � � java/time/LocalDateTime now ()Ljava/time/LocalDateTime; � yyyy-MM-dd'T'HH':00'
 � � � � � "java/time/format/DateTimeFormatter 	ofPattern 8(Ljava/lang/String;)Ljava/time/format/DateTimeFormatter;
 � � � � format 8(Ljava/time/format/DateTimeFormatter;)Ljava/lang/String; �   � Clear        � Cloudy       3       C       P       c � Rain       G       M � Snow Code LineNumberTable LocalVariableTable this LWeatherApp; getWeatherData 0(Ljava/lang/String;)Lorg/json/simple/JSONObject; conn Ljava/net/HttpURLConnection; 
resultJson Ljava/lang/StringBuilder; scanner Ljava/util/Scanner; parser #Lorg/json/simple/parser/JSONParser; resultJsonObj Lorg/json/simple/JSONObject; Lorg/json/simple/JSONArray; index I temperatureData D weatherCondition Ljava/lang/String; relativeHumidity J windspeedData weatherData e Ljava/lang/Exception; locationName locationData location 	urlString StackMapTable resultsJsonObj url Ljava/net/URL; Ljava/io/IOException; i timeList currentTime currentDateTime Ljava/time/LocalDateTime; 	formatter $Ljava/time/format/DateTimeFormatter; formattedDateTime 
SourceFile WeatherApp.java BootstrapMethods"
#$% $& $java/lang/invoke/StringConcatFactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;( �https://api.open-meteo.com/v1/forecast?latitude=&longitude=&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles* Vhttps://geocoding-api.open-meteo.com/v1/search?name=&count=10&language=en&format=json InnerClasses- %java/lang/invoke/MethodHandles$Lookup/ java/lang/invoke/MethodHandles Lookup !            �   /     *� �    �        �        � �   	 � �  �  d    r*� L+� � M,� � � J, � � � 9)� "  :� &:� * ȟ � 06� 8�� >Y� @:	� AY� C� G:

� J� 	
� N� RW���
� V� Y� \Y� ^:	� _� e� :i� � :k� � :� m6q� � :� � � 9s� � :� � u� w� {:� � :� � u� w7�� � :� � � 9� Y� �:�� �� �W�� �W�� �� �W�� �� �W�:� ��  3 Ni � Ohi �  �   � %          )  3 ! : % E & M ' O + X , f - n / | 3 � 6 � 9 � : � = � A � B � E � F � I � J � M N Q R, U5 VB WL XY Yf [i \k ]p ` �   �  :/ � �  X � � 	 f � � 
 � � � �  � � � �  � � j �  � � l   � �  � �   � � �  � � t   � s  g   X �  L	  , = � 5 4
 � k    r   m   d �  W   )I !  3?    < � O  `   ` +  �  > A� �  `   `  � 	    �  �     �*��� �K*� �  L+� &M,� * ȟ � 06� 8�� >Y� @N� AY,� C� G:� J� -� N� RW���� V,� Y� \Y� ^:-� _� e� :�� � :�M,� ��   ( � � ) � � �  �   N    f 	 i  n  r  s ' t ) w 1 x > { F | S � X � \ � e � s �  � � � � � � � �   \ 	 1 Q � �  > D � �  e  � �  s  �       m � �  �     �    y    % � ) ` +�  > A� .  ` `  � 
 ( )  �   �     $� �Y*� �L+� �� +M,�� �,� �,�L+� ��      �  �   "    � 	 �  �  �  �  �  � " � �   *  	     � �       $      ] � 
 o p  �   �     )� �L=*� �� *� � `N-+� �� ������    �       �  �  �  �  � ! � ' � �   *   
 l   !    )     %     �  `�  
 � Q  �   \     � �K˸ �L*+� �M,�    �       �  � 
 �  � �          
      
 } ~  �   �     ^�M	�� 	�M� P	��  ۔� 	�M� < ߔ�  ᔞ  㔛  唝 	�M�  锛  딝 �M,�    �   * 
   �  � 	 �  �  � # � C � I � Y � \ � �       ^ t    [     �  `         ! '! )+   
 ,.0 