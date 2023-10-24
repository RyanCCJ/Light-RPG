/* NCKU, EE.108B
   Ryan, Chung
*/

public class Character{

    protected int HP, MP, FP;
    
    public Character(int HP, int MP, int FP){
        
        this.HP = HP;
        this.MP = MP;
        this.FP = FP;
    }
    
    public int HP(){
        
        return HP;
    }
    
    public int MP(){
        
        return MP;
    }
    
    public int FP(){
        
        return FP;
    }
    
    public void iHP(int num){
        
        HP += num;
        if(HP>100)
            HP = 100;
    }
    
    public void dHP(int num){
        
        HP -= num;
        if(HP<0)
            HP = 0;
    }
    
    public void sHP(int num){
        
        HP = num;
    }
    
    public void iMP(int num){
        
        MP += num;
        if(MP>100)
            MP = 100;
    }
    
    public void dMP(int num){
        
        MP -= num;
        if(MP<0)
            MP = 0;
    }
    
    public void sMP(int num){
        
        MP = num;
    }
    
    public void iFP(int num){
        
        FP += num;
        if(FP>100)
            FP = 100;
    }
    
    public void dFP(int num){
        
        FP -= num;
        if(FP<0)
            FP = 0;
    }
    
    public void sFP(int num){
        
        FP = num;
    }
    
    public void rFP(){
        
        FP = 0;
    }
}