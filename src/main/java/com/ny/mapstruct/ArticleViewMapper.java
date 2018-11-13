/*
package com.ny.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ny.model.ArticleInfoView;
import com.ny.model.ArticleTypeCountView;
import com.ny.model.ArticleView;
import com.ny.model.CountClassView;
import com.ny.model.CountSchemaView;
import com.ny.model.CountTableIncrView;
import com.ny.model.CountTotalDataView;
import com.ny.model.PageInfoView;

*/
/**
 *
 *//*

@Mapper
public interface ArticleViewMapper {

    ArticleViewMapper INSTANCE = Mappers.getMapper(ArticleViewMapper.class);

    */
/**
     * ArticleEntity 转 ArticleView
     *
     * @param entity ArticleEntity
     * @return ArticleView
     *//*

    ArticleView articleToView(ArticleEntity entity);

    */
/**
     * ArticleEntities 转 ArticleViews
     *
     * @param entities List<ArticleEntity>
     * @return List<ArticleView>
     *//*

    List<ArticleView> articleListToViews(List<ArticleEntity> entities);

    */
/**
     * ArticleTypeCountEntity 转 ArticleTypeCountView
     *
     * @param entity ArticleTypeCountEntity
     * @return ArticleTypeCountView
     *//*

    ArticleTypeCountView articleTypeCountToView(ArticleTypeCountEntity entity);

    */
/**
     * ArticleTypeCountEntities 转 ArticleTypeCountViews
     *
     * @param entities List<ArticleTypeCountEntity>
     * @return List<ArticleTypeCountView>
     *//*

    List<ArticleTypeCountView> articleTypeCountListToViews(List<ArticleTypeCountEntity> entities);

    */
/**
     * CountSchemaEntity 转 CountSchemaView
     *
     * @param entity CountSchemaEntity
     * @return CountSchemaView
     *//*

    CountSchemaView countSchemaToView(CountSchemaEntity entity);

    */
/**
     * CountSchemaEntities 转 CountSchemaViews
     *
     * @param entities List<CountSchemaEntity>
     * @return List<CountSchemaView>
     *//*

    List<CountSchemaView> countSchemaListToViews(List<CountSchemaEntity> entities);

    */
/**
     * CountTableIncrEntity 转 CountTableIncrView
     *
     * @param entity CountTableIncrEntity
     * @return CountTableIncrView
     *//*

    CountTableIncrView countTableIncrToView(CountTableIncrEntity entity);

    */
/**
     * CountTableIncrEntities 转 CountTableIncrViews
     *
     * @param entities List<CountTableIncrEntity>
     * @return List<CountTableIncrView>
     *//*

    List<CountTableIncrView> countTableIncrListToViews(List<CountTableIncrEntity> entities);

    */
/**
     * CountTotalDataEntity 转 CountTotalDataView
     *
     * @param entity CountTotalDataEntity
     * @return CountTotalDataView
     *//*

    CountTotalDataView countTotalDataToView(CountTotalDataEntity entity);

    */
/**
     * CountTotalDataEntities 转 CountTotalDataViews
     *
     * @param entities List<CountTotalDataEntity>
     * @return List<CountTotalDataView>
     *//*

    List<CountTotalDataView> countTotalDataListToViews(List<CountTotalDataEntity> entities);

    */
/**
     * CountClassEntity 转 CountClassView
     *
     * @param entity CountClassEntity
     * @return CountClassView
     *//*

    CountClassView countClassToView(CountClassEntity entity);

    */
/**
     * CountClassEntities 转 CountClassViews
     *
     * @param entities List<CountClassEntity>
     * @return List<CountClassView>
     *//*

    List<CountClassView> countClassListToViews(List<CountClassEntity> entities);

    */
/**
     * ArticleEntities 转 ArticleViews
     *
     * @param entities PageInfoView<ArticleEntity>
     * @return PageInfoView<ArticleView>
     *//*

    PageInfoView<ArticleView> articleListToViews(PageInfoView<ArticleEntity> entities);

    */
/**
     * ArticleEntities 转 ArticleInfoViews
     *
     * @param entities PageInfoView<ArticleEntity>
     * @return PageInfoView<ArticleView>
     *//*

    PageInfoView<ArticleInfoView> articleListToInfoViews(PageInfoView<ArticleEntity> entities);
}
*/
