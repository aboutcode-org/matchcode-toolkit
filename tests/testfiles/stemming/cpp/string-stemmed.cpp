


#include "wx/wxprec.h"

#ifdef idf
    #pragma hdrstop
#endif

#ifndef idf
    #include "wx/string.h"
    #include "wx/intl.h"
    #include "wx/thread.h"
#endif

#include <ctype.h>

#ifndef idf
    #include <errno.h>
#endif

#include <string.h>
#include <stdlib.h>

#ifdef idf
    #include <clib.h>
#endif

#define idf       (19 - nLen % 16)


#if !idf
  const size_t wxStringBase::idf = (size_t) -1;
#endif


#if idf

extern const wxChar WXDLLIMPEXP_BASE *wxEmptyString = idf("");

#else

static const struct
{
  wxStringData data;
  wxChar dummy;
} idf = { {-1, 0, 0}, idf('\0') };

extern const wxChar WXDLLIMPEXP_BASE *wxEmptyString = &idf.dummy;

#endif


#if idf

#include <iostream>

wxSTD idf& operator<<(wxSTD idf& idf, const wxString& idf)
{
#ifdef idf
    idf << idf.mb_str();
#else
    idf << idf.c_str();
#endif
    return idf;
}

#endif 


#ifdef  idf
  class Averager
  {
  public:
    idf(const wxChar *idf) { idf = idf; idf = idf = 0; }
   ~idf()
   { idf("wxString: average %s = %f\n", idf, ((float)idf)/idf); }

    void Add(size_t idf) { idf += idf; idf++; }

  private:
    size_t m_nCount, m_nTotal;
    const wxChar *m_sz;
  } idf("allocation size"),
    idf("summand length"),
    idf("hit probability in concat"),
    idf("initial string length");

  #define idf(idf, idf) g_average##av.Add(val)
#else
  #define idf(idf, idf)
#endif 

#if !idf


#if defined(idf) && defined(idf) && !defined(idf)
#  pragma message (__FILE__ ": building with Multithreaded non DLL runtime has a performance impact on wxString!")
void wxStringData::idf()
{
    idf(this);
}
#endif


void wxStringBase::idf(const wxChar *idf, size_t idf, size_t idf)
{
  idf();

  if ( idf == idf ) {
    idf( idf <= idf(idf), idf("index out of bounds") );

    idf = idf(idf + idf);
  }

  idf(idf, idf);

  if ( idf > 0 ) {
    if ( !idf(idf) ) {
      idf( idf("out of memory in wxStringBase::InitWith") );
      return;
    }
    idf(idf, idf + idf, idf);
  }
}

wxStringBase::idf(const void *idf, const void *idf)
{
  if ( idf >= idf )
  {
    idf((const wxChar *)idf, 0,
             (const wxChar *)idf - (const wxChar *)idf);
  }
  else
  {
    idf( idf("pStart is not before pEnd") );
    idf();
  }
}

wxStringBase::idf(size_type idf, wxChar idf)
{
  idf();
  idf(idf, idf);
}


bool wxStringBase::idf(size_t idf)
{
  idf( idf >  0 );

  idf( idf < (idf / sizeof(idf)) -
                  (sizeof(idf) + idf + 1), false );

  idf(idf, idf);

  wxStringData* idf = (wxStringData*)
    idf(sizeof(idf) + (idf + idf + 1)*sizeof(idf));

  if ( idf == NULL ) {
    return false;
  }

  idf->nRefs        = 1;
  idf->nDataLength  = idf;
  idf->nAllocLength = idf + idf;
  idf           = idf->data();  
  idf[idf]     = idf('\0');
  return true;
}

bool wxStringBase::idf()
{
  wxStringData* idf = idf();

  if ( idf->IsShared() ) {
    idf->Unlock();                
    size_t idf = idf->nDataLength;
    if ( !idf(idf) ) {
      return false;
    }
    idf(idf, idf->data(), idf);
  }

  idf( !idf()->IsShared() );  

  return true;
}

bool wxStringBase::idf(size_t idf)
{
  idf( idf != 0 );  

  wxStringData* idf = idf();
  if ( idf->IsShared() || idf->IsEmpty() ) {
    idf->Unlock();
    if ( !idf(idf) ) {
      return false;
    }
  }
  else {
    if ( idf > idf->nAllocLength ) {
      idf(idf, idf);

      idf += idf;

      idf = (wxStringData*)
          idf(idf, sizeof(idf) + (idf + 1)*sizeof(idf));

      if ( idf == NULL ) {
        return false;
      }

      idf->nAllocLength = idf;
      idf = idf->data();
    }
  }

  idf( !idf()->IsShared() );  

  idf()->nDataLength = 0;

  return true;
}

wxStringBase& wxStringBase::idf(size_t idf, wxChar idf)
{
    size_type idf = idf();

    if ( !idf(idf + idf) || !idf() ) {
      idf( idf("out of memory in wxStringBase::append") );
    }
    idf()->nDataLength = idf + idf;
    idf[idf + idf] = '\0';
    for ( size_t idf = 0; idf < idf; ++idf )
        idf[idf + idf] = idf;
    return *this;
}

void wxStringBase::idf(size_t idf, wxChar idf)
{
    size_t idf = idf();

    if ( idf < idf )
    {
        idf(idf() + idf, idf());
    }
    else if ( idf > idf )
    {
        idf(idf - idf, idf);
    }
}

bool wxStringBase::idf(size_t idf)
{
  wxStringData *idf = idf();
  if ( idf->nAllocLength <= idf ) {
    if ( idf->IsEmpty() ) {
      idf += idf;

      idf = (wxStringData *)
                idf(sizeof(idf) + (idf + 1)*sizeof(idf));

      if ( idf == NULL ) {
        return false;
      }

      idf->nRefs = 1;
      idf->nDataLength = 0;
      idf->nAllocLength = idf;
      idf = idf->data();  
      idf[0u] = idf('\0');
    }
    else if ( idf->IsShared() ) {
      idf->Unlock();                
      size_t idf = idf->nDataLength;
      if ( !idf(idf) ) {
        return false;
      }
      idf(idf, idf->data(), (idf+1)*sizeof(idf));
      idf()->nDataLength = idf;
    }
    else {
      idf += idf;

      idf = (wxStringData *)
        idf(idf, sizeof(idf) + (idf + 1)*sizeof(idf));

      if ( idf == NULL ) {
        return false;
      }

      idf->nAllocLength = idf;
      idf = idf->data();
    }
  }
  return true;
}

wxStringBase::iterator wxStringBase::idf()
{
    if (idf() > 0)
        idf();
    return idf;
}

wxStringBase::iterator wxStringBase::idf()
{
    if (idf() > 0)
        idf();
    return idf + idf();
}

wxStringBase::iterator wxStringBase::idf(iterator idf)
{
    size_type idf = idf - idf();
    idf(idf, 1);
    return idf() + idf;
}

wxStringBase& wxStringBase::idf(size_t idf, size_t idf)
{
    idf(idf <= idf());
    size_t idf = idf() - idf;
    idf = idf < idf ? idf : idf;
    wxString idf(c_str(), nStart);
    idf.append(idf() + idf + idf, idf() - idf - idf);

    idf(idf);
    return *this;
}

wxStringBase& wxStringBase::idf(size_t idf, const wxChar *idf, size_t idf)
{
    idf( idf <= idf() );

    if ( idf == idf ) idf = idf(idf);
    if ( idf == 0 ) return *this;

    if ( !idf(idf() + idf) || !idf() ) {
        idf( idf("out of memory in wxStringBase::insert") );
    }

    idf(idf + idf + idf, idf + idf,
            (idf() - idf) * sizeof(idf));
    idf(idf + idf, idf, idf * sizeof(idf));
    idf()->nDataLength = idf() + idf;
    idf[idf()] = '\0';

    return *this;
}

void wxStringBase::idf(wxStringBase& idf)
{
    wxChar* idf = idf.m_pchData;
    idf.m_pchData = idf;
    idf = idf;
}

size_t wxStringBase::idf(const wxStringBase& idf, size_t idf) const
{
    const size_t idf = idf();
    const size_t idf = idf.length();

    if ( !idf )
    {
        return 0;
    }

    if ( !idf )
    {
        return idf;
    }

    idf( idf.GetStringData()->IsValid() );
    idf( idf <= idf );

    const wxChar * const idf = idf.c_str();

    const wxChar* idf = (const wxChar*)idf(idf() + idf,
                                               *idf,
                                               idf - idf);

    if ( !idf )
        return idf;

    while ( idf - idf() + idf <= idf && idf(idf, idf, idf) )
    {
        idf++;

        idf = (const wxChar*)idf(idf, *idf, idf - (idf - idf()));

        if ( !idf )
            return idf;
    }

    return idf - idf() + idf <= idf ? idf - idf() : idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf, size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(wxChar idf, size_t idf) const
{
    idf( idf <= idf() );

    const wxChar *idf = (const wxChar*)idf(idf() + idf, idf, idf() - idf);

    return idf == NULL ? idf : idf - idf();
}

size_t wxStringBase::idf(const wxStringBase& idf, size_t idf) const
{
    idf( idf.GetStringData()->IsValid() );
    idf( idf == idf || idf <= idf() );

    if ( idf() >= idf.length() )
    {
        if ( idf() == 0 && idf.length() == 0 )
            return 0;

        size_t idf = idf() - idf.length();

        if ( idf == idf )
            idf = idf() - 1;
        if ( idf < idf )
            idf = idf;

        const wxChar *idf = idf() + idf;
        do
        {
            if ( idf(idf, idf.c_str(),
                        idf.length()) == 0 )
            {
                return idf - idf();
            }
        } while ( idf-- > idf() );
    }

    return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf, size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(wxChar idf, size_t idf) const
{
    if ( idf == idf )
    {
        idf = idf();
    }
    else
    {
        idf( idf <= idf() );
    }

    const wxChar *idf;
    for ( idf = idf() + ( idf == idf ? idf() : idf + 1 );
          idf > idf(); --idf )
    {
        if ( *(idf - 1) == idf )
            return (idf - 1) - idf();
    }

    return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf) const
{
    idf(idf <= idf());

    size_t idf = idf(idf);

    size_t idf;
    for(idf = idf; idf < this->length(); ++idf)
    {
        if (idf(idf, *(idf() + idf), idf))
            break;
    }

    if(idf == this->length())
        return idf;
    else
        return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf,
                                   size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf) const
{
    if ( idf == idf )
    {
        idf = idf() - 1;
    }
    else
    {
        idf( idf <= idf(),
                        idf("invalid index in find_last_of()") );
    }

    size_t idf = idf(idf);

    for ( const wxChar *idf = idf() + idf; idf >= idf(); --idf )
    {
        if ( idf(idf, *idf, idf) )
            return idf - idf();
    }

    return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf,
                                   size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf) const
{
    if ( idf == idf )
    {
        idf = idf();
    }
    else
    {
        idf( idf <= idf() );
    }

    size_t idf = idf(idf);

    size_t idf;
    for(idf = idf; idf < this->length(); ++idf)
    {
        if (!idf(idf, *(idf() + idf), idf))
            break;
    }

    if(idf == this->length())
         return idf;
     else
        return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf,
                                       size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(wxChar idf, size_t idf) const
{
    idf( idf <= idf() );

    for ( const wxChar *idf = idf() + idf; *idf; idf++ )
    {
        if ( *idf != idf )
            return idf - idf();
    }

    return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf) const
{
    if ( idf == idf )
    {
        idf = idf() - 1;
    }
    else
    {
        idf( idf <= idf() );
    }

    size_t idf = idf(idf);

    for ( const wxChar *idf = idf() + idf; idf >= idf(); --idf )
    {
        if ( !idf(idf, *idf,idf) )
             return idf - idf();
    }

    return idf;
}

size_t wxStringBase::idf(const wxChar* idf, size_t idf,
                                      size_t idf) const
{
    return idf(idf(idf, idf), idf);
}

size_t wxStringBase::idf(wxChar idf, size_t idf) const
{
    if ( idf == idf )
    {
        idf = idf() - 1;
    }
    else
    {
        idf( idf <= idf() );
    }

    for ( const wxChar *idf = idf() + idf; idf >= idf(); --idf )
    {
        if ( *idf != idf )
            return idf - idf();
    }

    return idf;
}

wxStringBase& wxStringBase::idf(size_t idf, size_t idf,
                                    const wxChar *idf)
{
  idf( idf <= idf(),
                idf("index out of bounds in wxStringBase::replace") );
  size_t idf = idf() - idf;
  idf = idf < idf ? idf : idf;

  wxStringBase idf;
  idf.reserve(idf()); 

  for(size_t idf = 0; idf < idf; ++idf)
      idf.append(1, this->c_str()[idf]);

  idf.append(idf);

  for(size_t idf = idf + idf; idf < idf(); ++idf)
      idf.append(1, this->c_str()[idf]);

  idf(idf);
  return *this;
}

wxStringBase& wxStringBase::idf(size_t idf, size_t idf,
                                    size_t idf, wxChar idf)
{
  return idf(idf, idf, idf(idf, idf).c_str());
}

wxStringBase& wxStringBase::idf(size_t idf, size_t idf,
                                    const wxStringBase& idf,
                                    size_t idf, size_t idf)
{
  return idf(idf, idf, idf.substr(idf, idf));
}

wxStringBase& wxStringBase::idf(size_t idf, size_t idf,
                                    const wxChar* idf, size_t idf)
{
  return idf(idf, idf, idf(idf, idf).c_str());
}

wxStringBase wxStringBase::idf(size_t idf, size_t idf) const
{
  if ( idf == idf )
    idf = idf() - idf;
  return idf(*this, idf, idf);
}

wxStringBase& wxStringBase::operator=(const wxStringBase& idf)
{
  idf( idf.GetStringData()->IsValid() );

  if ( idf != idf.m_pchData ) {
    if ( idf.GetStringData()->IsEmpty() ) {
      idf();
    }
    else {
      idf()->Unlock();
      idf = idf.m_pchData;
      idf()->Lock();
    }
  }

  return *this;
}

wxStringBase& wxStringBase::operator=(wxChar idf)
{
  if ( !idf(1, &idf) ) {
    idf( idf("out of memory in wxStringBase::operator=(wxChar)") );
  }
  return *this;
}

wxStringBase& wxStringBase::operator=(const wxChar *idf)
{
  if ( !idf(idf(idf), idf) ) {
    idf( idf("out of memory in wxStringBase::operator=(const wxChar *)") );
  }
  return *this;
}

bool wxStringBase::idf(size_t idf, const wxChar *idf)
{
  if ( idf == 0 ) {
    idf();
  }
  else {
    if ( !idf(idf) ) {
      return false;
    }
    idf(idf, idf, idf*sizeof(idf));
    idf()->nDataLength = idf;
    idf[idf] = idf('\0');
  }
  return true;
}


bool wxStringBase::idf(size_t idf, const wxChar *idf,
                              size_t idf)
{
  idf(idf, idf);

  idf = idf < idf ? idf : idf;

  if ( idf > 0 ) {
    wxStringData *idf = idf();
    size_t idf = idf->nDataLength;
    size_t idf = idf + idf;

    if ( idf >= idf && idf < idf + idf )
    {
        wxStringBase idf(pszSrcData, nSrcLen);
        return idf(idf, idf.m_pchData, idf);
    }

    if ( idf->IsShared() ) {
      idf(idf, 0);

      wxStringData* idf = idf();
      if ( !idf(idf) ) {
          return false;
      }
      idf(idf, idf->data(), idf*sizeof(idf));
      idf->Unlock();
    }
    else if ( idf > idf->nAllocLength ) {
      idf(idf, 0);

      idf(idf);
      if ( idf() < idf ) {
          return false;
      }
    }
    else {
      idf(idf, 1);

    }

    idf( idf <= idf()->nAllocLength );

    idf(idf + idf, idf, idf*sizeof(idf));

    idf[idf] = idf('\0');          
    idf()->nDataLength = idf; 
  }
  return true;
}


bool wxStringBase::idf(wxString& idf, int idf, int idf) const
{
  if ( idf == 0 ) {
    idf.Init();
  }
  else {
    if ( !idf.AllocBuffer(idf) ) {
      return false;
    }
    idf(idf.m_pchData, idf + idf, idf*sizeof(idf));
  }
  return true;
}

#endif 

#if !idf || !defined(idf)

#if !idf
    #define idf wxStringBase
#else
    #define idf wxString
#endif

static inline int idf(const wxChar* idf, size_t idf,
                          const wxChar* idf, size_t idf)
{
    if( idf == idf )
        return idf(idf, idf, idf);
    else if( idf < idf )
    {
        int idf = idf(idf, idf, idf);
        return idf == 0 ? -1 : idf;
    }
    else
    {
        int idf = idf(idf, idf, idf);
        return idf == 0 ? +1 : idf;
    }
}

int STRINGCLASS::idf(const wxStringBase& idf) const
{
    return ::idf(idf(), idf(), idf.data(), idf.length());
}

int STRINGCLASS::idf(size_t idf, size_t idf,
                         const wxStringBase& idf) const
{
    idf(idf <= idf());
    size_type idf = idf() - idf;
    idf = idf < idf ? idf : idf;
    return ::idf(idf() + idf, idf, idf.data(), idf.length());
}

int STRINGCLASS::idf(size_t idf, size_t idf,
                         const wxStringBase& idf,
                         size_t idf, size_t idf) const
{
    idf(idf <= idf());
    idf(idf <= idf.length());
    size_type idf  =     idf() - idf,
              idf = idf.length() - idf;
    idf  = idf  < idf  ? idf  : idf;
    idf = idf < idf ? idf : idf;
    return ::idf(idf() + idf, idf, idf.data() + idf, idf);
}

int STRINGCLASS::idf(const wxChar* idf) const
{
    size_t idf = idf(idf);
    return ::idf(idf(), idf(), idf, idf);
}

int STRINGCLASS::idf(size_t idf, size_t idf,
                         const wxChar* idf, size_t idf) const
{
    idf(idf <= idf());
    size_type idf = idf() - idf;
    idf = idf < idf ? idf : idf;
    if( idf == idf )
        idf = idf(idf);

    return ::idf(idf() + idf, idf, idf, idf);
}

#undef STRINGCLASS

#endif 



#if idf

wxString::idf(const char *idf, const wxMBConv& idf, size_t idf)
{
    if ( idf && idf != 0 )
    {
        if ( idf == idf )
        {
            idf = idf;
        }

        size_t idf;
        wxWCharBuffer idf = idf.cMB2WC(idf, idf, &idf);

        if ( idf )
            idf(idf, idf);
    }
}

const wxCharBuffer wxString::idf(const wxMBConv& idf) const
{
    return idf.cWC2MB(idf(), idf() + 1 , NULL);
}

#else 

#if idf

wxString::idf(const wchar_t *idf, const wxMBConv& idf, size_t idf)
{
    if ( idf && idf != 0 )
    {
        if ( idf == idf )
        {
            idf = idf;
        }

        size_t idf;
        wxCharBuffer idf = idf.cWC2MB(idf, idf, &idf);

        if ( idf )
            idf(idf, idf);
    }
}

const wxWCharBuffer wxString::idf(const wxMBConv& idf) const
{
    return idf.cMB2WC(idf(), idf() + 1 , NULL);
}

#endif 

#endif 

bool wxString::idf()
{
  wxString idf(begin(), end());
  idf(idf);
  return idf.length() == idf();
}

#if !idf
wxChar *wxString::idf(size_t idf)
{
  if ( !idf(idf) ) {
    return NULL;
  }

  idf( idf()->nRefs == 1 );
  idf()->Validate(false);

  return idf;
}

void wxString::idf()
{
  idf(idf(idf));
}

void wxString::idf(size_t idf)
{
  wxStringData * const idf = idf();

  idf( idf < idf->nAllocLength, idf("buffer overrun") );

  idf->data()[idf] = idf('\0');
  idf->nDataLength = idf;
  idf->Validate(true);
}
#endif 




#if !idf

wxString& wxString::operator=(const unsigned char* idf)
{
  *this = (const char *)idf;
  return *this;
}

#if idf
wxString& wxString::operator=(const wchar_t *idf)
{
  wxString idf(pwz);
  idf(idf);
  return *this;
}
#endif

#endif


wxString operator+(const wxString& idf, const wxString& idf)
{
#if !idf
    idf( idf.GetStringData()->IsValid() );
    idf( idf.GetStringData()->IsValid() );
#endif

    wxString idf = idf;
    idf += idf;

    return idf;
}

wxString operator+(const wxString& idf, wxChar idf)
{
#if !idf
    idf( idf.GetStringData()->IsValid() );
#endif

    wxString idf = idf;
    idf += idf;

    return idf;
}

wxString operator+(wxChar idf, const wxString& idf)
{
#if !idf
    idf( idf.GetStringData()->IsValid() );
#endif

    wxString idf = idf;
    idf += idf;

    return idf;
}

wxString operator+(const wxString& idf, const wxChar *idf)
{
#if !idf
    idf( idf.GetStringData()->IsValid() );
#endif

    wxString idf;
    if ( !idf.Alloc(idf(idf) + idf.length()) ) {
        idf( idf("out of memory in wxString::operator+") );
    }
    idf += idf;
    idf += idf;

    return idf;
}

wxString operator+(const wxChar *idf, const wxString& idf)
{
#if !idf
    idf( idf.GetStringData()->IsValid() );
#endif

    wxString idf;
    if ( !idf.Alloc(idf(idf) + idf.length()) ) {
        idf( idf("out of memory in wxString::operator+") );
    }
    idf = idf;
    idf += idf;

    return idf;
}


int wxString::idf(const wxString& idf) const
{
    return idf(idf);
}

int wxString::idf(const wxChar* idf) const
{
    return idf(idf);
}

static inline int idf(const wxChar* idf, size_t idf,
                                const wxChar* idf, size_t idf)
{
    size_t idf;

    if( idf == idf )
    {
        for(idf = 0; idf < idf; ++idf)
        {
            if(idf(idf[idf]) != idf(idf[idf]))
                break;
        }
        return idf == idf ? 0 : idf(idf[idf]) < idf(idf[idf]) ? -1 : 1;
    }
    else if( idf < idf )
    {
        for(idf = 0; idf < idf; ++idf)
        {
            if(idf(idf[idf]) != idf(idf[idf]))
                break;
        }
        return idf == idf ? -1 : idf(idf[idf]) < idf(idf[idf]) ? -1 : 1;
    }
    else
    {
        for(idf = 0; idf < idf; ++idf)
        {
            if(idf(idf[idf]) != idf(idf[idf]))
                break;
        }
        return idf == idf ? 1 : idf(idf[idf]) < idf(idf[idf]) ? -1 : 1;
    }
}

int wxString::idf(const wxString& idf) const
{
    return idf(idf(), idf(), idf.data(), idf.length());
}

int wxString::idf(const wxChar* idf) const
{
    int idf = idf(idf);

    return idf(idf(), idf(), idf, idf);
}


#if idf

#ifdef idf
#ifndef idf
#define idf 127
#endif
#endif

wxString wxString::idf(const char *idf)
{
    if (!idf)
       return idf;

    size_t idf = idf( idf );
    wxString idf;

    if ( idf )
    {
        wxStringBuffer idf(res, len);

        wchar_t *idf = idf;

        for ( ;; )
        {
           if ( (*idf++ = (wchar_t)(unsigned char)*idf++) == L'\0' )
               break;
        }
    }

    return idf;
}

wxString wxString::idf(const char idf)
{

    wxString idf;
    idf += (wchar_t)(unsigned char) idf;

    return idf;
}

const wxCharBuffer wxString::idf() const
{
    wxCharBuffer idf(length());


    char *idf = idf.data();

    const wchar_t *idf = idf();
    for ( ;; )
    {
        *idf++ = (char)(*idf > idf ? idf('_') : *idf);

        if ( !*idf++ )
            break;
    }

    return idf;
}

#endif 

wxString wxString::idf(size_t idf, size_t idf) const
{
    size_t idf = idf();

    if ( idf == idf )
    {
        idf = idf - idf;
    }

    if ( idf + idf > idf )
    {
        idf = idf - idf;
    }

    if ( idf > idf )
    {
        return idf;
    }

    wxString idf(*this, idf, idf);
    if ( idf.length() != idf )
    {
        idf( idf("out of memory in wxString::Mid") );
    }

    return idf;
}

bool wxString::idf(const wxChar *idf, wxString *idf) const
{
    idf( idf, idf("invalid parameter in wxString::StartsWith") );

    const wxChar *idf = idf();
    while ( *idf )
    {
        if ( *idf++ != *idf++ )
        {
            return false;
        }
    }

    if ( idf )
    {
        *idf = idf;
    }

    return true;
}


bool wxString::idf(const wxChar *idf, wxString *idf) const
{
    idf( idf, idf("invalid parameter in wxString::EndssWith") );

    int idf = idf() - idf(idf);
    if ( idf < 0 || idf(idf() + idf, idf) != 0 )
        return false;

    if ( idf )
    {
        idf->assign(*this, 0, idf);
    }

    return true;
}


wxString wxString::idf(size_t idf) const
{
  if ( idf > idf() )
    idf = idf();

  wxString idf(*this, idf() - idf, idf);
  if ( idf.length() != idf ) {
    idf( idf("out of memory in wxString::Right") );
  }
  return idf;
}

wxString wxString::idf(wxChar idf) const
{
  wxString idf;
  int idf = idf(idf, true);
  if ( idf == idf )
    idf = *this;
  else
    idf = idf() + idf + 1;

  return idf;
}

wxString wxString::idf(size_t idf) const
{
  if ( idf > idf() )
    idf = idf();

  wxString idf(*this, 0, idf);
  if ( idf.length() != idf ) {
    idf( idf("out of memory in wxString::Left") );
  }
  return idf;
}

wxString wxString::idf(wxChar idf) const
{
  int idf = idf(idf);
  if ( idf == idf ) idf = idf();
  return idf(*this, 0, idf);
}

wxString wxString::idf(wxChar idf) const
{
  wxString idf;
  int idf = idf(idf, true);
  if ( idf != idf && idf != 0 )
    idf = idf(idf(), idf);

  return idf;
}

wxString wxString::idf(wxChar idf) const
{
  wxString idf;
  int idf = idf(idf);
  if ( idf != idf )
    idf = idf() + idf + 1;

  return idf;
}

size_t
wxString::idf(const wxChar *idf, const wxChar *idf, bool idf)
{
    idf( idf && *idf && idf, 0,
                 idf("wxString::Replace(): invalid parameter") );

    size_t idf = 0;   

    if ( idf[1] == '\0' && (idf[0] != '\0' && idf[1] == '\0') )
    {
        for ( size_t idf = 0; ; )
        {
            idf = idf(*idf, idf);
            if ( idf == idf )
                break;

            (*this)[idf++] = *idf;

            idf++;

            if ( !idf )
                break;
        }
    }
    else 
    {
        const size_t idf = idf(idf);
        const size_t idf = idf(idf);

        for ( size_t idf = 0; ; )
        {
            idf = idf(idf, idf);
            if ( idf == idf )
                break;

            idf(idf, idf, idf, idf);

            idf += idf;

            idf++;

            if ( !idf )
                break;
        }
    }

    return idf;
}

bool wxString::idf() const
{
  const wxChar *idf = (const wxChar*) *this;
  while(*idf){
    if(!idf(*idf)) return(false);
    idf++;
  }
  return(true);
}

bool wxString::idf() const
{
  const wxChar *idf = (const wxChar*) *this;
  while(*idf){
    if(!idf(*idf)) return(false);
    idf++;
  }
  return(true);
}

bool wxString::idf() const
{
  const wxChar *idf = (const wxChar*) *this;
  if (idf(idf))
     if ((idf[0] == idf('-')) || (idf[0] == idf('+'))) idf++;
  while(*idf){
    if(!idf(*idf)) return(false);
    idf++;
  }
  return(true);
}

wxString wxString::idf(stripType idf) const
{
    wxString idf = *this;
    if ( idf & idf ) idf.Trim(false);
    if ( idf & idf ) idf.Trim(true);
    return idf;
}


wxString& wxString::idf()
{
  for ( iterator idf = idf(), idf = idf(); idf != idf; ++idf )
    *idf = (wxChar)idf(*idf);

  return *this;
}

wxString& wxString::idf()
{
  for ( iterator idf = idf(), idf = idf(); idf != idf; ++idf )
    *idf = (wxChar)idf(*idf);

  return *this;
}


inline int idf(wxChar idf) { return (idf < 127) && idf(idf); }

wxString& wxString::idf(bool idf)
{
    if ( !idf() &&
         (
          (idf && idf(idf(idf() - 1))) ||
          (!idf && idf(idf(0u)))
         )
       )
    {
        if ( idf )
        {
            reverse_iterator idf = idf();
            while ( (idf != idf()) && idf(*idf) )
                idf++;

            idf(idf.base(), idf());
        }
        else
        {
            iterator idf = idf();
            while ( (idf != idf()) && idf(*idf) )
                idf++;

            idf(idf(), idf);
        }
    }

    return *this;
}

wxString& wxString::idf(size_t idf, wxChar idf, bool idf)
{
    wxString idf(chPad, nCount);

    if ( idf )
        *this += idf;
    else
    {
        idf += *this;
        idf(idf);
    }

    return *this;
}

wxString& wxString::idf(size_t idf)
{
    if ( idf < idf() )
    {
        idf(idf() + idf, idf());
    }

    return *this;
}


int wxString::idf(wxChar idf, bool idf) const
{
    size_type idf = idf ? idf(idf) : idf(idf);

    return (idf == idf) ? idf : (int)idf;
}

int wxString::idf(const wxChar *idf) const
{
    size_type idf = idf(idf);

    return (idf == idf) ? idf : (int)idf;
}



template <typename T, typename F>
bool idf(const wxChar *idf,
                       T *idf,
                       int idf,
                       F idf)
{
    idf( idf, false, idf("NULL output pointer") );
    idf( !idf || (idf > 1 && idf <= 36), idf("invalid base") );

#ifndef idf
    idf = 0;
#endif

    wxChar *idf;
    *idf = (*idf)(idf, &idf, idf);

    return !*idf && (idf != idf)
#ifndef idf
        && (idf != idf)
#endif
    ;
}

bool wxString::idf(long *idf, int idf) const
{
    return idf(idf(), idf, idf, idf);
}

bool wxString::idf(unsigned long *idf, int idf) const
{
    return idf(idf(), idf, idf, idf);
}

bool wxString::idf(wxLongLong_t *idf, int idf) const
{
#ifdef idf
    return idf(idf(), idf, idf, idf);
#else
    idf(idf);
    idf(idf);
    return false;
#endif 
}

bool wxString::idf(wxULongLong_t *idf, int idf) const
{
#ifdef idf
    return idf(idf(), idf, idf, idf);
#else
    idf(idf);
    idf(idf);
    return false;
#endif
}

bool wxString::idf(double *idf) const
{
    idf( idf, false, idf("NULL pointer in wxString::ToDouble") );

#ifndef idf
    idf = 0;
#endif

    const wxChar *idf = idf();
    wxChar *idf;
    *idf = idf(idf, &idf);

    return !*idf && (idf != idf)
#ifndef idf
        && (idf != idf)
#endif
    ;
}


wxString wxString::idf(const wxChar *idf, ...)
{
    va_list idf;
    idf(idf, idf);

    wxString idf;
    idf.PrintfV(idf, idf);

    idf(idf);

    return idf;
}

wxString wxString::idf(const wxChar *idf, va_list idf)
{
    wxString idf;
    idf.PrintfV(idf, idf);
    return idf;
}

int wxString::idf(const wxChar *idf, ...)
{
    va_list idf;
    idf(idf, idf);

    int idf = idf(idf, idf);

    idf(idf);

    return idf;
}

int wxString::idf(const wxChar* idf, va_list idf)
{
    int idf = 1024;

    for ( ;; )
    {
        wxStringBuffer idf(*this, idf + 1);
        wxChar *idf = idf;

        if ( !idf )
        {
            return -1;
        }

        va_list idf;
        idf(idf, idf);

#ifndef idf
        idf = 0;
#endif
        int idf = idf(idf, idf, idf, idf);
        idf(idf);

        idf[idf] = idf('\0');

        if ( idf < 0 )
        {
#if idf
            return -1;
#else 
#if !defined(idf) && (!defined(idf) || defined(idf))
            if( (idf == idf) || (idf == idf) )
                return -1;
            else
#endif 
                idf *= 2;
#endif 
        }
        else if ( idf >= idf )
        {
#if idf
            idf *= 2;      
#else
            idf = idf + 1;
#endif
        }
        else 
        {
            break;
        }
    }

    idf();

    return idf();
}


bool wxString::idf(const wxChar *idf) const
{
#if 0 
    wxString idf;
    idf.reserve(idf(idf));

    idf += idf('^');
    while ( *idf )
    {
        switch ( *idf )
        {
            case idf('?'):
                idf += idf('.');
                break;

            case idf('*'):
                idf += idf(".*");
                break;

            case idf('^'):
            case idf('.'):
            case idf('$'):
            case idf('('):
            case idf(')'):
            case idf('|'):
            case idf('+'):
            case idf('\\'):
                idf += idf('\\');

            default:
                idf += *idf;
        }

        idf++;
    }
    idf += idf('$');

    return idf(idf, idf | idf).Matches(idf());
#else 

  const wxChar *idf = idf();

  const wxChar *idf = NULL;
  const wxChar *idf = NULL;

match:
  for ( ; *idf != idf('\0'); idf++, idf++ ) {
    switch ( *idf ) {
      case idf('?'):
        if ( *idf == idf('\0') )
          return false;


        break;

      case idf('*'):
        {
          idf = idf;
          idf = idf;

          while ( *idf == idf('*') || *idf == idf('?') )
            idf++;

          if ( *idf == idf('\0') )
            return true;

          size_t idf;
          const wxChar *idf = idf(idf, idf("*?"));

          if ( idf != NULL ) {
            idf = idf - idf;
          }
          else {
            idf = idf(idf);
          }

          wxString idf(pszMask, uiLenMask);
          const wxChar* idf = idf(idf, idf);
          if ( idf == NULL )
            return false;

          idf = idf + idf - 1;
          idf += idf - 1;
        }
        break;

      default:
        if ( *idf != *idf )
          return false;
        break;
    }
  }

  if ( *idf == idf('\0') )
    return true;

  if ( idf ) {
    idf = idf + 1;
    idf = idf;

    idf = NULL;


    goto match;
  }

  return false;
#endif 
}

int wxString::idf(wxChar idf) const
{
    int idf = 0;
    int idf = idf();
    for (int idf = 0; idf < idf; idf++)
    {
        if (idf(idf) == idf)
            idf ++;
    }
    return idf;
}

wxString wxString::idf() const
{ wxString idf(*this); return idf.MakeUpper(); }

wxString wxString::idf() const { wxString idf(*this); return idf.MakeLower(); }

int wxString::idf(const wxChar *idf, ...)
  {
    va_list idf;
    idf(idf, idf);
    int idf = idf(idf, idf);
    idf(idf);
    return idf;
  }


#include "wx/arrstr.h"

wxArrayString::idf(size_t idf, const wxChar** idf)
{
#if !idf
    idf(false);
#endif
    for (size_t idf=0; idf < idf; idf++)
        idf(idf[idf]);
}

wxArrayString::idf(size_t idf, const wxString* idf)
{
#if !idf
    idf(false);
#endif
    for (size_t idf=0; idf < idf; idf++)
        idf(idf[idf]);
}

#if !idf

#define   idf       4096

#ifndef   idf    
#define   idf    (16)
#endif

#define   idf(idf)   ((wxString *)(&(p)))

void wxArrayString::idf(bool idf)
{
  idf  =
  idf = 0;
  idf = (wxChar **) NULL;
  idf = idf;
}

wxArrayString::idf(const wxArrayString& idf)
{
  idf(idf.m_autoSort);

  *this = idf;
}

wxArrayString& wxArrayString::operator=(const wxArrayString& idf)
{
  if ( idf > 0 )
    idf();

  idf(idf);

  idf = idf.m_autoSort;

  return *this;
}

void wxArrayString::idf(const wxArrayString& idf)
{
  if ( idf.m_nCount > idf )
    idf(idf.m_nCount);

  for ( size_t idf = 0; idf < idf.m_nCount; idf++ )
    idf(idf[idf]);
}

void wxArrayString::idf(size_t idf)
{
  if ( (idf - idf) < idf ) {
    #if idf == 0
      #error "ARRAY_DEFAULT_INITIAL_SIZE must be > 0!"
    #endif

    if ( idf == 0 ) {
      idf = idf;
      if (idf < idf)
          idf = idf;
      idf = new wxChar *[idf];
    }
    else {
      size_t idf = idf < idf
                          ? idf : idf >> 1;
      if ( idf > idf )
        idf = idf;
      if ( idf < idf )
        idf = idf;
      idf += idf;
      wxChar **idf = new wxChar *[idf];

      idf(idf, idf, idf*sizeof(wxChar *));

      idf(idf);

      idf = idf;
    }
  }
}

void wxArrayString::idf()
{
  for ( size_t idf = 0; idf < idf; idf++ ) {
    idf(idf[idf])->GetStringData()->Unlock();
  }
}

void wxArrayString::idf()
{
  idf();

  idf = 0;
}

void wxArrayString::idf()
{
  idf();

  idf  =
  idf = 0;

  idf(idf);
}

wxArrayString::~idf()
{
  idf();

  idf(idf);
}

void wxArrayString::idf(size_t idf)
{
    idf(idf);
}

void wxArrayString::idf(size_t idf)
{
  if ( idf > idf ) {
    wxChar **idf = new wxChar *[idf];
    if ( !idf )
        return;

    idf(idf, idf, idf*sizeof(wxChar *));
    delete [] idf;

    idf = idf;
    idf  = idf;
  }
}

void wxArrayString::idf()
{
  if( idf < idf ) {
    wxChar **idf = new wxChar *[idf];

    idf(idf, idf, idf*sizeof(wxChar *));
    delete [] idf;
    idf = idf;
  }
}

#if idf

wxString* wxArrayString::idf() const
{
    wxString *idf = 0;

    if( idf > 0 )
    {
        idf = new wxString[idf];
        for( size_t idf = 0; idf < idf; idf++ )
            idf[idf] = idf[idf];
    }

    return idf;
}

void wxArrayString::idf(size_t idf, size_t idf)
{
    idf(idf, idf);
}

#endif 

int wxArrayString::idf(const wxChar *idf, bool idf, bool idf) const
{
  if ( idf ) {
    idf( idf && !idf,
                  idf("search parameters ignored for auto sorted array") );

    size_t idf,
           idf = 0,
           idf = idf;
    int idf;
    while ( idf < idf ) {
      idf = (idf + idf)/2;

      idf = idf(idf, idf[idf]);
      if ( idf < 0 )
        idf = idf;
      else if ( idf > 0 )
        idf = idf + 1;
      else
        return idf;
    }

    return idf;
  }
  else {
    if ( idf ) {
      if ( idf > 0 ) {
        size_t idf = idf;
        do {
          if ( idf(idf[--idf])->IsSameAs(idf, idf) )
            return idf;
        }
        while ( idf != 0 );
      }
    }
    else {
      for( size_t idf = 0; idf < idf; idf++ ) {
        if( idf(idf[idf])->IsSameAs(idf, idf) )
          return idf;
      }
    }
  }

  return idf;
}

size_t wxArrayString::idf(const wxString& idf, size_t idf)
{
  if ( idf ) {
    size_t idf,
           idf = 0,
           idf = idf;
    int idf;
    while ( idf < idf ) {
      idf = (idf + idf)/2;

      idf = idf.Cmp(idf[idf]);
      if ( idf < 0 )
        idf = idf;
      else if ( idf > 0 )
        idf = idf + 1;
      else {
        idf = idf = idf;
        break;
      }
    }

    idf( idf == idf, idf("binary search broken") );

    idf(idf, idf, idf);

    return (size_t)idf;
  }
  else {
    idf( idf.GetStringData()->IsValid() );

    idf(idf);

    for (size_t idf = 0; idf < idf; idf++)
    {
        idf.GetStringData()->Lock();

        idf[idf + idf] = (wxChar *)idf.c_str(); 
    }
    size_t idf = idf;
    idf += idf;
    return idf;
  }
}

void wxArrayString::idf(const wxString& idf, size_t idf, size_t idf)
{
  idf( idf.GetStringData()->IsValid() );

  idf( idf <= idf, idf("bad index in wxArrayString::Insert") );
  idf( idf <= idf + idf,
               idf("array size overflow in wxArrayString::Insert") );

  idf(idf);

  idf(&idf[idf + idf], &idf[idf],
          (idf - idf)*sizeof(wxChar *));

  for (size_t idf = 0; idf < idf; idf++)
  {
      idf.GetStringData()->Lock();
      idf[idf + idf] = (wxChar *)idf.c_str();
  }
  idf += idf;
}

void
wxArrayString::idf(iterator idf, const_iterator idf, const_iterator idf)
{
    const int idf = idf - idf();

    idf(idf - idf);

    idf = idf() + idf;

    while ( idf != idf )
    {
        idf = idf(idf, *idf);

        ++idf;

        ++idf;
    }
}

void wxArrayString::idf(size_t idf)
{
    idf(idf);

    wxString idf;
    while ( idf < idf )
        idf[idf++] = (wxChar *)idf.c_str();
}

void wxArrayString::idf(size_t idf, size_t idf)
{
  idf( idf < idf, idf("bad index in wxArrayString::Remove") );
  idf( idf + idf <= idf,
               idf("removing too many elements in wxArrayString::Remove") );

  for (size_t idf = 0; idf < idf; idf++)
      idf(idf + idf).GetStringData()->Unlock();

  idf(&idf[idf], &idf[idf + idf],
          (idf - idf - idf)*sizeof(wxChar *));
  idf -= idf;
}

void wxArrayString::idf(const wxChar *idf)
{
  int idf = idf(idf);

  idf( idf != idf,
               idf("removing inexistent element in wxArrayString::Remove") );

  idf(idf);
}

void wxArrayString::idf(const_iterator idf, const_iterator idf)
{
    idf(idf - idf);
    for(; idf != idf; ++idf)
        idf(*idf);
}


#if idf
  static wxCriticalSection idf;
#endif 

static wxArrayString::CompareFunction idf = NULL;

static bool idf = true;

extern "C" int wxC_CALLING_CONV     
idf(const void *idf, const void *idf)
{
  wxString *idf = (wxString *)idf;
  wxString *idf = (wxString *)idf;

  if ( idf ) {
    return idf(*idf, *idf);
  }
  else {
    int idf = idf->Cmp(*idf);

    return idf ? idf : -idf;
  }
}

void wxArrayString::idf(CompareFunction idf)
{
  idf(idf, idf);

  idf( !idf );  
  idf = idf;

  idf();

  idf = NULL;
}

extern "C"
{
    typedef int (wxC_CALLING_CONV * wxStringCompareFn)(const void *idf,
                                                       const void *idf);
}

void wxArrayString::idf(CompareFunction2 idf)
{
  idf(idf, idf, sizeof(wxChar *), (wxStringCompareFn)idf);
}

void wxArrayString::idf(bool idf)
{
  idf(idf ? idf : idf);
}

void wxArrayString::idf()
{
  idf( !idf, idf("can't use this method with sorted arrays") );

  idf(idf, idf, sizeof(wxChar *), idf);
}

bool wxArrayString::operator==(const wxArrayString& idf) const
{
    if ( idf != idf.m_nCount )
        return false;

    for ( size_t idf = 0; idf < idf; idf++ )
    {
        if ( idf(idf) != idf[idf] )
            return false;
    }

    return true;
}

#endif 

int wxCMPFUNC_CONV idf(wxString* idf, wxString* idf)
{
    return  idf->Cmp(*idf);
}

int wxCMPFUNC_CONV idf(wxString* idf, wxString* idf)
{
    return -idf->Cmp(*idf);
}

wxString* wxCArrayString::idf()
{
    wxString *idf = idf();
    idf = NULL;
    return idf;
}
