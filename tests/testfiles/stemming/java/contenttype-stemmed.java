/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Contributors:     Mathias Bogaert
//                   joelr@viair.com

package idf.idf.idf.idf;

import idf.idf.idf.idf.idf;

/**
   <code>BoundedFIFO</code> serves as the bounded first-in-first-out
   buffer heavily used by the {@link org.apache.log4j.AsyncAppender}.
   
   @author Ceki G&uuml;lc&uuml; 
   @since version 0.9.1 */
public class idf {
  
  LoggingEvent[] idf;
  int idf = 0;
  int idf = 0;
  int idf = 0;
  int idf;

  /**
     Instantiate a new BoundedFIFO with a maximum size passed as argument.
   */
  public
  idf(int idf) {
   if(idf < 1) {
      throw new IllegalArgumentException("The maxSize argument ("+idf+
			    ") is not a positive integer.");
    }
    this.idf = idf;
    idf = new LoggingEvent[idf];
  }
  
  /**
     Get the first element in the buffer. Returns <code>null</code> if
     there are no elements in the buffer.  */
  public
  LoggingEvent idf() {
    if(idf == 0) 
      return null;
    
    LoggingEvent idf = idf[idf];
    idf[idf] = null; // help garbage collection

    if(++idf == idf) {
	idf = 0;
    }
    idf--;    
    return idf;    
  }

  /**
     Place a {@link LoggingEvent} in the buffer. If the buffer is full
     then the event is <b>silently dropped</b>. It is the caller's
     responsability to make sure that the buffer has free space.  */
  public 
  void idf(LoggingEvent idf) {
    if(idf != idf) {      
      idf[idf] = idf;    
      if(++idf == idf) {
	idf = 0;
      }
      idf++;
    }
  }

  /**
     Get the maximum size of the buffer.
   */
  public 
  int idf() {
    return idf;
  }

  /**
     Return <code>true</code> if the buffer is full, that is, whether
     the number of elements in the buffer equals the buffer size. */
  public 
  boolean idf() {
    return idf == idf;
  }

  /**
     Get the number of elements in the buffer. This number is
     guaranteed to be in the range 0 to <code>maxSize</code>
     (inclusive).
  */
  public
  int idf() {
    return idf;
  } 


  int idf(int idf, int idf) {
    return idf < idf ? idf : idf;
  }


  /**
     Resize the buffer to a new size. If the new size is smaller than
     the old size events might be lost.
     
     @since 1.1
   */
  synchronized
  public 
  void idf(int idf) {
    if(idf == idf) 
      return;


   LoggingEvent[] idf = new LoggingEvent[idf];

   // we should not copy beyond the buf array
   int idf = idf - idf;

   // we should not copy beyond the tmp array
   idf = idf(idf, idf);

   // er.. how much do we actually need to copy?
   // We should not copy more than the actual number of elements.
   idf = idf(idf, idf);

   // Copy from buf starting a first, to tmp, starting at position 0, len1 elements.
   idf.idf(idf, idf, idf, 0, idf);
   
   // Are there any uncopied elements and is there still space in the new array?
   int idf = 0;
   if((idf < idf) && (idf < idf)) {
     idf = idf - idf;
     idf = idf(idf, idf - idf);
     idf.idf(idf, 0, idf, idf, idf);
   }
   
   this.idf = idf;
   this.idf = idf;    
   this.idf=0;   
   this.idf = idf+idf;
   this.idf = this.idf;
   if(this.idf == this.idf) // this should never happen, but again, it just might.
     this.idf = 0;
  }

  
  /**
     Returns <code>true</code> if there is just one element in the
     buffer. In other words, if there were no elements before the last
     {@link #put} operation completed.  */
  public
  boolean idf() {
    return idf == 1;
  }

  /**
      Returns <code>true</code> if the number of elements in the
      buffer plus 1 equals the maximum buffer size, returns
      <code>false</code> otherwise. */
  public
  boolean idf() {
    return (idf+1 == idf);
  }

}
