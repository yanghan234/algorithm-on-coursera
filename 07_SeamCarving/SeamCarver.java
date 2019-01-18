import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.IndexMinPQ;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.IllegalArgumentException;
import java.lang.Math;
public class SeamCarver
{
    private int width;
    private int height;
    private int[] prev;
    private double[] distTo;
    private Picture picture;
    private double[][] energyOfPicture;
    public SeamCarver(Picture picture) // create a seam carver object based on the given picutre
    {
        if ( picture == null )
            throw new IllegalArgumentException();
        this.width = picture.width();
        this.height = picture.height();
        this.picture = new Picture(picture);
        energyOfPicture = new double[width][height];
        for ( int icol = 0; icol < width; icol++ )
            for ( int irow = 0; irow < height; irow++ )
                energyOfPicture[icol][irow] = -1;
    }

    private double energyAt(int icol, int irow )
    {
        if ( icol == 0 || icol == width-1 || irow == 0 || irow == height-1 )
            return 1000.0;
        int upRGB = this.picture.getRGB(icol,irow-1);
        int downRGB = this.picture.getRGB(icol,irow+1);
        int leftRGB = this.picture.getRGB(icol-1,irow);
        int rightRGB = this.picture.getRGB(icol+1,irow);

        double res = 0.0;
        res += Math.pow(((upRGB>>16)&0xFF)-((downRGB>>16)&0xFF),2.0);
        res += Math.pow(((upRGB>> 8)&0xFF)-((downRGB>> 8)&0xFF),2.0);
        res += Math.pow(((upRGB>> 0)&0xFF)-((downRGB>> 0)&0xFF),2.0);

        res += Math.pow(((leftRGB>>16)&0xFF)-((rightRGB>>16)&0xFF),2.0);
        res += Math.pow(((leftRGB>> 8)&0xFF)-((rightRGB>> 8)&0xFF),2.0);
        res += Math.pow(((leftRGB>> 0)&0xFF)-((rightRGB>> 0)&0xFF),2.0);

        return Math.sqrt(res);
    }

    public Picture picture() // current picture
    {
        Picture tmpPic = new Picture(width,height);
        for ( int icol = 0; icol < width; icol++ )
            for ( int irow = 0; irow < height; irow++ )
                tmpPic.setRGB(icol,irow,picture.getRGB(icol,irow));
        return tmpPic;
    }

    public int width()      // width of current picture
    {
        return width;
    }

    public int height()     // height of current picture
    {
        return height;
    }

    public double energy(int x, int y)  // energy of pixel at column x and row y
    {
        if ( !( x >= 0 && x < width && y >= 0 && y < height ) )
            throw new IllegalArgumentException();
        if ( energyOfPicture[x][y] < 0 )
            energyOfPicture[x][y] = energyAt(x,y);
        return energyOfPicture[x][y];
    }

    public int[] findHorizontalSeam()   // sequence of indices for horizontal seam
    {
        this.prev = new int[width*height];
        this.distTo = new double[width*height];
        for ( int icol = 0; icol < width-1; icol++ )
        {
            for ( int irow = 0; irow < height; irow++ )
            {
                if ( icol == 0 )
                    distTo[irow*width+icol] = energy(icol,irow);
                else
                    distTo[irow*width+icol] = Double.POSITIVE_INFINITY;
                prev[irow*width+icol] = irow*width+icol;
            }
        }
        for ( int irow = 0; irow < height; irow++ )
        {
            int icol = width-1;
            distTo[irow*width+icol] = Double.POSITIVE_INFINITY;
            prev[irow*width+icol] = irow*width+icol;
        }

        this.HorizontalAcyclicDAG();

        int minIndex = width-1;
        for ( int irow = 0; irow < height; irow++ )
            if ( distTo[minIndex] > distTo[irow*width+width-1] )
                minIndex = irow*width+width-1;

        int[] res = new int[width];
        while ( minIndex != prev[minIndex] )
        {
            int irow = minIndex/width;
            int icol = minIndex%width;
            res[icol] = irow;
            minIndex = prev[minIndex];
        }
        res[0] = minIndex/width;
        this.distTo = null;
        this.prev = null;
        return res;
    }

    public int[] findVerticalSeam()     // sequence of indices for vertical seam
    {
        this.prev = new int[width*height];
        this.distTo = new double[width*height];
        for ( int icol = 0; icol < width; icol++ )
        {
            for ( int irow = 0; irow < height-1; irow++ )
            {
                if ( irow == 0 )
                    distTo[irow*width+icol] = 1000.0;
                else
                    distTo[irow*width+icol] = Double.POSITIVE_INFINITY;
                prev[irow*width+icol] = irow*width+icol;
            }
            int irow = height-1;
            distTo[irow*width+icol] = Double.POSITIVE_INFINITY;
            prev[irow*width+icol] = irow*width+icol;
        }

        this.VerticalAcyclicDAG();

        int minIndex = (height-1)*width+0;
        for ( int icol = 0; icol < width; icol++ )
            if ( distTo[minIndex] > distTo[(height-1)*width+icol] )
                minIndex = (height-1)*width+icol;

        int[] res = new int[height];
        while ( minIndex != prev[minIndex] )
        {
            int irow = minIndex/width;
            int icol = minIndex%width;
            res[irow] = icol;
            minIndex = prev[minIndex];
        }
        res[0] = minIndex;
        this.distTo = null;
        this.prev = null;
        return res;
    }

    private void VerticalAcyclicDAG()
    {
        for ( int icol = 0; icol < width; )
        {
            int nextcol = icol+1;
            int irow = 0;
            while ( irow < height && icol >= 0 )
            {
                int index = irow*width+icol;
                energy(icol,irow);
                if ( irow+1 < height ) relax(index+width,index);
                if ( irow+1 < height && icol < width-1 ) relax(index+width+1,index);
                if ( irow+1 < height && icol > 0 ) relax(index+width-1,index);
                icol--;
                irow++;
            }
            icol = nextcol;
        }
        if ( height == 1 )
            return;
        for ( int irow = 1; irow < height; )
        {
            int nextrow = irow+1;
            int icol = width-1;
            while ( icol >= 0 && irow < height )
            {
                int index = irow*width+icol;
                energy(icol,irow);
                if ( irow+1 < height ) relax(index+width,index);
                if ( irow+1 < height && icol < width-1 ) relax(index+width+1,index);
                if ( irow+1 < height && icol > 0 ) relax(index+width-1,index);
                icol--;
                irow++;
            }
            irow = nextrow;
        }
    }

    private void HorizontalAcyclicDAG()
    {
        for ( int irow = 0; irow < height; )
        {
            int nextrow = irow+1;
            int icol = 0;
            while ( icol < width && irow >= 0 )
            {
                int index = irow*width+icol;
                energy(icol,irow);
                if ( icol+1 < width ) relax(index+1,index);
                if ( icol+1 < width && irow < height-1 ) relax(index+width+1,index);
                if ( icol+1 < width && irow > 0 ) relax(index-width+1,index);
                icol++;
                irow--;
            }
            irow = nextrow;
        }
        if ( width == 1 )
            return;
        for ( int icol = 1; icol < width; )
        {
            int nextcol = icol+1;
            int irow = height-1;
            while ( irow >= 0 && icol < width )
            {
                int index = irow*width+icol;
                energy(icol,irow);
                if ( icol+1 < width ) relax(index+1,index);
                if ( icol+1 < width && irow < height-1 ) relax(index+width+1,index);
                if ( icol+1 < width && irow > 0 ) relax(index-width+1,index);
                icol++;
                irow--;
            }
            icol = nextcol;
        }
    }

    private void relax(int v,int w)
    {
        int irow = v/width;
        int icol = v%width;
        double thisEnergy = energy(icol,irow);
        if ( distTo[v] > distTo[w] + thisEnergy )
        {
            prev[v] = w;
            distTo[v] = distTo[w] + thisEnergy;
        }
    }

    public void removeHorizontalSeam(int[] seam)    // remove horizontal seam from current picuture
    {
        if ( seam == null )
            throw new IllegalArgumentException();
        if ( seam.length != width )
            throw new IllegalArgumentException();
        int last = -1;
        for ( int icol = 0; icol < width; icol++ )
        {
            if ( !( seam[icol] >= 0 && seam[icol] < height ) )
                throw new IllegalArgumentException();
            if ( last != -1 && Math.abs(last-seam[icol]) > 1 )
                throw new IllegalArgumentException();
            last = seam[icol];
            int irow = 0;
            while ( irow < seam[icol] )
            {
                picture.setRGB(icol,irow,this.picture.getRGB(icol,irow));
                energyOfPicture[icol][irow] = -1;
                irow++;
            }
            while ( irow < height-1 )
            {
                picture.setRGB(icol,irow,this.picture.getRGB(icol,irow+1));
                energyOfPicture[icol][irow] = -1;
                irow++;
            }
        }
        this.height--;
    }

    public void removeVerticalSeam(int[] seam)      // remove vertical seam from current picture
    {
        if ( seam == null )
            throw new IllegalArgumentException();
        if ( seam.length != height )
            throw new IllegalArgumentException();
        int last = -1;
        for ( int irow = 0; irow < height; irow++ )
        {
            if ( !( seam[irow] >= 0 && seam[irow] < width ) )
                throw new IllegalArgumentException();
            if ( last != -1 && Math.abs(seam[irow]-last) > 1 )
                throw new IllegalArgumentException();
            last = seam[irow];
            int icol = 0;
            while ( icol < seam[irow] )
            {
                picture.setRGB(icol,irow,this.picture.getRGB(icol,irow));
                energyOfPicture[icol][irow] = -1;
                icol++;
            }
            while ( icol < width-1 )
            {
                picture.setRGB(icol,irow,this.picture.getRGB(icol+1,irow));
                energyOfPicture[icol][irow] = -1;
                icol++;
            }
        }
        this.width--;
    }

    public static void main(String[] args)
    {
        Picture pic = new Picture("./test/4x6.png");
        SeamCarver sc = new SeamCarver(pic);
        int[] seam = sc.findHorizontalSeam();
        //sc.display();
        for ( int i = 0; i < seam.length; i++ )
            System.out.printf("%d, ",seam[i]);
        System.out.printf("\n");
    }
}
