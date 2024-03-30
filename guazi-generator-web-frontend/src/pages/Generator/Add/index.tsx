import FileUploader from '@/components/FileUploader';
import PictureUploader from '@/components/PictureUploader';
import { COS_HOST } from '@/constants';
import FileConfigForm from '@/pages/Generator/Add/components/FileConfigForm';
import GeneratorMaker from '@/pages/Generator/Add/components/GeneratorMaker';
import ModelConfigForm from '@/pages/Generator/Add/components/ModelConfigForm';
import {
  addGeneratorUsingPost,
  editGeneratorUsingPost,
  getGeneratorVoByIdUsingGet,
} from '@/services/backend/generatorController';
import { useSearchParams } from '@@/exports';
import type { ProFormInstance } from '@ant-design/pro-components';
import {
  ProCard,
  ProFormItem,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
  StepsForm,
} from '@ant-design/pro-components';
import { history } from '@umijs/max';
import { message, UploadFile } from 'antd';
import React, { useEffect, useRef, useState } from 'react';

/**
 * 生成器创建页面
 * @constructor
 */
const GeneratorAddPage: React.FC = () => {
  const [searchParams] = useSearchParams();
  const id = searchParams.get('id');
  const [oldData, setOldData] = useState<API.GeneratorEditRequest>();
  const formRef = useRef<ProFormInstance>();
  // 记录表单信息
  const [basicInfo, setBasicInfo] = useState<API.GeneratorEditRequest>();
  const [modelConfig, setModleConfig] = useState<API.ModelConfig>();
  const [fileConfig, setFileConfig] = useState<API.FileConfig>();

  /**
   * 更新
   * @param values
   */
  const doUpdate = async (values: API.GeneratorEditRequest) => {
    try {
      const res = await editGeneratorUsingPost(values);
      if (res.data) {
        message.success('更新成功');
        history.push(`/generator/detail/${id}`);
      }
    } catch (e: any) {
      message.error('更新失败， ' + e.message);
    }
  };
  /**
   * 创建
   * @param values
   */
  const doAdd = async (values: API.GeneratorAddRequest) => {
    try {
      const res = await addGeneratorUsingPost(values);
      if (res.data) {
        message.success('创建成功');
        history.push(`/generator/detail/${res.data}`);
      }
    } catch (e: any) {
      message.error('创建失败， ' + e.message);
    }
  };
  /**
   * 提交
   * @param values
   */
  const doSubmit = async (values: API.GeneratorAddRequest) => {
    // 校验数据
    if (!values.fileConfig) {
      values.fileConfig = {};
    }
    if (!values.modelConfig) {
      values.modelConfig = {};
    }
    if (values.distPath && values.distPath.length > 0) {
      // @ts-ignore
      values.distPath = values.distPath[0].response;
    }
    // 调用接口
    if (id) {
      await doUpdate({
        // @ts-ignore
        id,
        ...values,
      });
    } else {
      await doAdd({ ...values });
    }
  };

  /**
   * 加载数据
   */
  const loadData = async () => {
    if (!id) {
      return;
    }
    try {
      // @ts-ignore
      const res = await getGeneratorVoByIdUsingGet({ id });

      // 处理文件路径
      if (res.data) {
        const { distPath } = res.data ?? {};
        if (distPath) {
          // @ts-ignore
          res.data.distPath = [
            {
              uid: id,
              name: '文件' + id,
              url: COS_HOST + distPath,
              response: distPath,
            } as UploadFile,
          ];
        }
        setOldData(res.data);
        formRef.current?.setFieldsValue(res.data);
      }
    } catch (e: any) {
      message.error('加载数据失败， ' + e.messages);
    }
  };

  useEffect(() => {
    if (id) {
      loadData();
    }
  }, [id]);
  return (
    <ProCard>
      {/* 创建或已加载更新数据时才渲染那表单*/}
      {(!id || oldData) && (
        <StepsForm<API.GeneratorAddRequest | API.GeneratorEditRequest>
          formRef={formRef}
          onFinish={doSubmit}
          formProps={{
            initialValues: oldData,
          }}
        >
          <StepsForm.StepForm
            name="base"
            title="基本信息"
            onFinish={async (values) => {
              setBasicInfo(values);
              return true;
            }}
          >
            <ProFormText name="name" label="名称" placeholder="请输入名称" />
            <ProFormTextArea name="description" label="描述" placeholder="请输入名描述" />
            <ProFormText name="basePackage" label="基础包" placeholder="请输入基础包" />
            <ProFormText name="version" label="版本" placeholder="请输入版本" />
            <ProFormText name="author" label="作者" placeholder="请输入作者" />
            <ProFormSelect name="tags" label="标签" mode="tags" placeholder="请输入标签列表" />
            <ProFormItem name="picture" label="图片">
              <PictureUploader biz="generator_picture" />
            </ProFormItem>
          </StepsForm.StepForm>
          <StepsForm.StepForm
            name="fileConfig"
            title="文件配置"
            onFinish={async (values) => {
              setFileConfig(values);
              return true;
            }}
          >
            <FileConfigForm formRef={formRef} oldData={oldData} />
          </StepsForm.StepForm>
          <StepsForm.StepForm
            name="modelConfig"
            title="模型配置"
            onFinish={async (values) => {
              setModleConfig(values);
              return true;
            }}
          >
            <ModelConfigForm formRef={formRef} oldData={oldData} />
          </StepsForm.StepForm>
          <StepsForm.StepForm name="dist" title="生成器文件">
            <ProFormItem name="distPath" label="产物包">
              <FileUploader biz="generator_dist" description="请上传生成器文件压缩包" />
            </ProFormItem>

            <GeneratorMaker
              meta={{
                ...basicInfo,
                ...fileConfig,
                ...modelConfig,
              }}
            />
          </StepsForm.StepForm>
        </StepsForm>
      )}
    </ProCard>
  );
};

export default GeneratorAddPage;
