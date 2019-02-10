#include <Pixy2.h>

int visionTarget=2;
int cargo=1;
int printMode=0;

float focal=1;
float height=1;

const int avNum=10;

int averageArray[avNum];
int arrayIndex=0;

Pixy2 pixy;

void setup(){
  Serial.begin(115200);
//  Serial.print("Starting...\n");
  int i;
  for(i=0;i<avNum;i++){
    averageArray[i]=-1;
  }
  pixy.init();
}

void loop(){ 
  // grab blocks!
  if (Serial.available()){
    switch (Serial.read()){
      case 'v':
        visionTarget=Serial.read()-48;
        break;
      case 'c':
        cargo=Serial.read()-48;
        break;
      case 'p':
        printMode=Serial.read()-48;
        break;
      //case 'd':
        //f=Serial.readStringUntil("\n").toFloat();
    }
  }
  
  pixy.ccc.getBlocks();
  if (pixy.ccc.numBlocks)
  {
    String text = "blocks:"+String(pixy.ccc.numBlocks);
    if (pixy.ccc.blocks[0].m_signature==visionTarget and pixy.ccc.blocks[1].m_signature==visionTarget and pixy.ccc.numBlocks==2){
      averageArray[arrayIndex]=(pixy.ccc.blocks[0].m_x+pixy.ccc.blocks[1].m_x)/2;
      text=text+",tape-x:"+String(myAverage(averageArray));
      int tapey=(pixy.ccc.blocks[0].m_y+pixy.ccc.blocks[1].m_y)/2;
      text=text+",tape-y:"+String(tapey);
      text=text+",tape-h:"+String((pixy.ccc.blocks[0].m_height+pixy.ccc.blocks[1].m_height)/2);
      text=text+",tape-w:"+String((pixy.ccc.blocks[0].m_width+pixy.ccc.blocks[1].m_width)/2);
      text=text+",tape-o:"+String(abs(int(pixy.ccc.blocks[0].m_x)-int(pixy.ccc.blocks[1].m_x)));
      text=text+",tape-d:"+String(focal*height/tapey);
    } else{
      text=text+",tape-x:none";
      text=text+",tape-y:none";
      text=text+",tape-h:none";
      text=text+",tape-w:none";
      text=text+",tape-o:none";
      text=text+",tape-d:none";
      averageArray[arrayIndex]=-1;
    }
    if (pixy.ccc.blocks[0].m_signature==cargo){
      text=text+",cargo-x:"+String(pixy.ccc.blocks[0].m_x);
      text=text+",cargo-y:"+String(pixy.ccc.blocks[0].m_y);
    }else{
       text=text+",cargo-x:none";
       text=text+",cargo-y:none";
    }
    if (printMode==0){
      Serial.println(text);
    }else if(printMode==1){
      for (int i=0; i<pixy.ccc.numBlocks; i++){
        Serial.print(i);
        pixy.ccc.blocks[i].print();
      }
    }
  }else{
    averageArray[arrayIndex]=-1;
  }
  arrayIndex++;
  if(arrayIndex==avNum){
    arrayIndex=0;  
  }
}
int myAverage(int nums[]){
    int total=0;
    int num=0;
    int i;
    for(i=0; i<avNum;i++){
        if (nums[i]!=-1){
          total=total+nums[i];
          num++;
        }
    }
    return(int(total/num));
}

class smoother{
  public:
  const static int avNum=10;
  int avNums[avNum];
  int index=0;
  
  int average() {
    int total=0;
    int num=0;
    int i;
    for(i=0; i<avNum;i++){
        if (avNums[i]!=-1){
          total=total+avNums[i];
          num++;
        }
      }
    return(int(total/num));
  }
  void add(int num){
    avNums[index]=num;
    index++;
    if (index==avNum){
      index=0;
    }
  }
};















