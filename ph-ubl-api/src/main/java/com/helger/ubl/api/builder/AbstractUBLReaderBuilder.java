/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.ubl.api.builder;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Source;

import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.xml.transform.TransformSourceFactory;
import com.helger.ubl.api.IUBLDocumentType;

/**
 * Abstract builder class for reading UBL documents.
 *
 * @author Philip Helger
 * @param <T>
 *          The UBL implementation class to be read
 * @param <IMPLTYPE>
 *          The implementation class implementing this abstract class.
 */
public abstract class AbstractUBLReaderBuilder <T, IMPLTYPE extends AbstractUBLReaderBuilder <T, IMPLTYPE>>
                                               extends AbstractUBLBuilder <IMPLTYPE>
{
  protected ValidationEventHandler m_aEventHandler;

  public AbstractUBLReaderBuilder (@Nonnull final IUBLDocumentType aDocType)
  {
    super (aDocType);
  }

  /**
   * Set the JAXB validation event handler to be used. May be <code>null</code>.
   *
   * @param aEventHandler
   *          The event handler to be used. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public IMPLTYPE setValidationEventHandler (@Nullable final ValidationEventHandler aEventHandler)
  {
    m_aEventHandler = aEventHandler;
    return thisAsT ();
  }

  /**
   * Interpret the passed {@link File} as a UBL document.
   *
   * @param aSource
   *          The source to read from. May not be <code>null</code>.
   * @return The evaluated UBL document or <code>null</code> in case of a
   *         parsing error
   */
  @Nullable
  public T read (@Nonnull final File aSource)
  {
    return read (TransformSourceFactory.create (aSource));
  }

  /**
   * Interpret the passed {@link IReadableResource} as a UBL document.
   *
   * @param aSource
   *          The source to read from. May not be <code>null</code>.
   * @return The evaluated UBL document or <code>null</code> in case of a
   *         parsing error
   */
  @Nullable
  public T read (@Nonnull final IReadableResource aSource)
  {
    return read (TransformSourceFactory.create (aSource));
  }

  /**
   * Interpret the passed {@link byte[]} as a UBL document.
   *
   * @param aSource
   *          The source to read from. May not be <code>null</code>.
   * @return The evaluated UBL document or <code>null</code> in case of a
   *         parsing error
   */
  @Nullable
  public T read (@Nonnull final byte [] aSource)
  {
    return read (TransformSourceFactory.create (aSource));
  }

  /**
   * Interpret the passed {@link byte[]} as a UBL document.
   *
   * @param sSource
   *          The source to read from. May not be <code>null</code>.
   * @return The evaluated UBL document or <code>null</code> in case of a
   *         parsing error
   */
  @Nullable
  public T read (@Nonnull final String sSource)
  {
    return read (TransformSourceFactory.create (sSource));
  }

  @Nullable
  public abstract T read (@Nonnull Source aSource);
}