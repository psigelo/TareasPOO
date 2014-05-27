import java.awt.*;
import java.awt.geom.*;

public class ElasticView {
   private static final double xPoints[]={0,1.0};
   private static final double yPoints[]={0,0};
   private static final Path2D.Double polyline = 
                      new Path2D.Double(Path2D.WIND_EVEN_ODD,xPoints.length);
   private Path2D.Double shape;
   private Stroke stroke;
   private boolean isSelected;
   private Elastic elastic;   

   static {  // static initialization block. It creates a spring of length = 1.
      polyline.moveTo (xPoints[0], yPoints[0]);
      for (int index = 1; index < xPoints.length;index++)
         polyline.lineTo(xPoints[index], yPoints[index]);
   }
   public ElasticView(Elastic elastic) {
      isSelected=false;
      this.elastic = elastic;
      double  x;
         this.elastic = elastic;
         AffineTransform at = AffineTransform.getTranslateInstance(0,0);
          if(this.elastic.a_end != null && this.elastic.b_end !=null){
            x = Math.abs(this.elastic.a_end.obtener_extremo_derecho() - this.elastic.b_end.obtener_extremo_izquierdo());
         }
         else{
            x = 1.0;
         }
         at.rotate(x, 0);
         at.scale(Math.abs(x), elastic.getRestLength());
         shape = (Path2D.Double) at.createTransformedShape(polyline);
         stroke = new BasicStroke(0.02f);
      
   }
   public void updateView (Graphics2D g){
      if(this.elastic.a_end != null && this.elastic.b_end !=null){
         double ax=this.elastic.a_end.obtener_extremo_derecho();
         double xa_b = Math.abs(this.elastic.a_end.obtener_extremo_derecho() - this.elastic.b_end.obtener_extremo_izquierdo());
         AffineTransform at = AffineTransform.getTranslateInstance(ax, 0);
         at.rotate(xa_b, 0);
         at.scale(Math.abs(xa_b),  elastic.getRestLength());
         shape = (Path2D.Double) at.createTransformedShape(polyline);
         if (Math.abs(xa_b) < elastic.getRestLength())
            g.setColor(Color.ORANGE);
         else
            g.setColor(Color.RED);
         g.setStroke(stroke);
      }
      g.setStroke(stroke);
      g.draw(shape);
   }
   public boolean contains(double x, double y){
      return shape.getBounds2D().contains(x,y);
   }
   public void setSelected() {
      isSelected = true;
   }
   public void setReleased() {
      isSelected=false;
   }

   public boolean getIsSelected(){
      return isSelected;
   }

}