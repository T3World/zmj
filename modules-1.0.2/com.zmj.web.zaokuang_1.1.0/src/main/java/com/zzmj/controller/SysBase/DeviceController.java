package com.zzmj.controller.SysBase;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import com.zzmj.pojo.entity.ZZDevice;
import com.zzmj.pojo.vo.DeviceWithAttribute;
import com.zzmj.pojo.vo.SysResult;
import com.zzmj.service.ZZDeviceService;
import com.zzmj.util.ErrorUtil;
import com.zzmj.util.exception.DoSqlFailedException;
import com.zzmj.util.exception.EmptyResultException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/SysBase/Device")
public class DeviceController {

	// 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认） 
    public final static String IMG_PATH_PREFIX = "static/upload/img";


	Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ZZDeviceService zzDeviceService;

/*	@RequestMapping(value = "/addZZDevice", method = RequestMethod.POST)
	public SysResult addZZDevice(@RequestParam("file")MultipartFile file,
			ZZDevice zzDevice,HttpServletRequest request) {
		 	
			String originalFileName = file.getOriginalFilename();
	        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
	        String uuid = UUID.randomUUID().toString();
	        String newFile = uuid + suffix;
	        
	        //动态获取服务器路径
	        String realPath=request.getServletContext().getRealPath("/upload");
	        System.out.println(realPath+"+++++");
	        
	        File file1=new  File(realPath);
	        if(!file1.exists()){
	        	file1.mkdirs();
	        }
	        
	        File newFile1 = new File(file1.getAbsolutePath() + File.separator + newFile);
	        
	        
	        try {
				//file.transferTo(new File(file1+"/"+newFile));
	        	FileUtils.copyInputStreamToFile(file.getInputStream(), newFile1);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return null;     
	}*/
	
	
	@RequestMapping(value = "/addZZDevice", method = RequestMethod.POST)
	public SysResult addZZDevice(@RequestParam("file")MultipartFile file,
	HttpServletRequest request, HttpSession session,ZZDevice zzDevice) {
		
		// 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());
		
		String originalFileName=file.getOriginalFilename();
		String suffix=originalFileName.substring(originalFileName.lastIndexOf("."));
		
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + suffix;
         
         try {
        	// 构建真实的文件路径
             File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
             System.out.println(newFile.getAbsolutePath());

             // 上传图片到 -》 “绝对路径”
             //file.transferTo(newFile.getAbsoluteFile());
             FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
             
             zzDevice.setDeviceImg("/img/"+filename);
             
             try {
     			return this.zzDeviceService.addZZDevice(zzDevice);
     		} catch (Exception e) {
     			e.printStackTrace();
     			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
     		}
             
		} catch (Exception e) {
			e.printStackTrace();
		    logger.info("图片上传失败，出现异常！", e);
		    return new SysResult(ErrorUtil.CODE2001, "图片上传失败", null);
		}
	}
	
	
	/*@RequestMapping(value = "/addZZDevice", method = RequestMethod.POST)
	public SysResult addZZDevice(@RequestParam("file")MultipartFile file, ZZDevice zzDevice) {
		 	
			String originalFileName = file.getOriginalFilename();
	        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
	        String uuid = UUID.randomUUID().toString();
	        String newFile = uuid + suffix;
	        
	        String realPath = new String("D:/file/file");	//上传到本地磁盘D盘下  如果没有该文件  并且自动创建file文件夹		       
	        File file1 = new File(realPath);
	        if(!file1.exists()){
	            // 递归生成文件夹
	        	file1.mkdirs();
	        }
	        
	        File file2 = new File(file1.getAbsolutePath() + File.separator + newFile);
	        try {
				FileUtils.copyInputStreamToFile( file.getInputStream(), file2);
				
				zzDevice.setDeviceImg(newFile);
				
				try {
					return zzDeviceService.addZZDevice(zzDevice);
				} catch (Exception e) {
					e.printStackTrace();
					return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("图片上传失败，出现异常！", e);
			    return new SysResult(ErrorUtil.CODE2001, "图片上传失败", null);
			}	        
	}*/
	
	
	@RequestMapping(value = "/download")
	public void download(@RequestParam("filename")String filename,HttpServletResponse response,HttpServletRequest request) {
		
		 //String realPath = request.getServletContext().getRealPath("/upload");
		 String realPath = new String("src/main/resources/" + IMG_PATH_PREFIX);	 
		 //String realPath = new String("D:/file");			 	
		 File file = new File(realPath);
		 File file1 = new File(file.getAbsolutePath()+"/"+filename);
		 		 
		 try {
			response.setHeader("content-disposition", "attchment;filename="+URLEncoder.encode(filename,"utf-8"));
			try {
				FileUtils.copyFile(file1,response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		 } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	

	@RequestMapping(value = "/delZZDevice", method = RequestMethod.POST)
	public SysResult delZZDevice(ZZDevice zzDevice) {
		try {
			return this.zzDeviceService.delZZDevice(zzDevice);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/updateZZdevice", method = RequestMethod.POST)
	public SysResult updateZZdevice(ZZDevice zzDevice) {
		try {
			return this.zzDeviceService.updateZZdevice(zzDevice);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 注意 查询出的每个对象 isdel应为0
	 * 
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/selectById", method = RequestMethod.POST)
	public SysResult selectById(String deviceId) {
		try {
			return this.zzDeviceService.selectByid(deviceId);
		} catch (DoSqlFailedException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据设备类型id 查询设备表集合 且查询出的对象的isDel属性为0
	 * 
	 * @param deviceTypeId
	 * @return
	 */
	@RequestMapping(value = "/deviceTypeId", method = RequestMethod.POST)
	public SysResult selectBydevicetypeId(String deviceTypeId) {
		try {
			return zzDeviceService.selectByDeviceTypeId(deviceTypeId);

		} catch (EmptyResultException e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 对设备表进行分页
	 * 
	 * @param devicetypeId
	 * @param keyword
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getDeviceData", method = RequestMethod.POST)
	public SysResult getDeviceData(@RequestParam(name = "deviceTypeId", required = false) String deviceTypeId,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "pageNo", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		try {
			return this.zzDeviceService.listZZDevicePage(deviceTypeId, keyword, pageNo, pageSize);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	/**
	 * 根据工作面id 设备类型id 查询设备表集合
	 * 
	 * @param deviceTypeId
	 * @param workfaceId
	 * @return
	 */
	@RequestMapping(value = "/selectDeviceByWorkfaceAndDeviceTypeId", method = RequestMethod.POST)
	public SysResult selectDeviceByWorkfaceAndDeviceTypeId(
			@RequestParam(name = "deviceTypeId", required = false) String deviceTypeId,
			@RequestParam(name = "workfaceId", required = false) String workfaceId) {
		try {
			return this.zzDeviceService.selectDeviceByWorkfaceAndDeviceTypeId(workfaceId, deviceTypeId);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

	@RequestMapping(value = "/batDelZZDevice", method = RequestMethod.POST)
	public SysResult batDelZZDevice(@RequestParam(name = "ids", required = false) String ids) {
		try {
			return this.zzDeviceService.batchDel(ids);
		} catch (DoSqlFailedException e) {
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
	}

    @RequestMapping(value = "/listDeviceAttributeAllByDeviceId", method = RequestMethod.POST)
    public SysResult listDeviceAttributeAllByDeviceId(@RequestParam(name = "deviceId") String deviceId) {
        try {
            return this.zzDeviceService.listDeviceAttributeAllByDeviceId(deviceId);
        } catch (DoSqlFailedException e) {
            return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/selectDeviceWithAttributeByDeviceId", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributeByDeviceId(@RequestParam(name = "deviceId") String deviceId) {
            try {
				return this.zzDeviceService.selectDeviceWithAttributeByDeviceId(deviceId);
			} catch (Exception e) {
				e.printStackTrace();
				 return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
			}
    }


    @RequestMapping(value = "/updateDeviceWithAttributeByDeviceId", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult selectDeviceWithAttributeByDeviceId(@RequestBody DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.updateDeviceWithAttributeByDeviceId(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }

    @RequestMapping(value = "/addDeviceWithAttribute", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult addDeviceWithAttribute(@RequestBody(required = false)  DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.addDeviceWithAttribute(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/deleteDeviceWithAttributeByDeviceId", method = RequestMethod.POST,consumes = "application/json;charset=utf-8")
    public SysResult deleteDeviceWithAttributeByDeviceId(@RequestBody(required = false)  DeviceWithAttribute deviceWithAttribute) {
    	try {
			return zzDeviceService.deleteDeviceWithAttributeByDeviceId(deviceWithAttribute);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/selectDeviceWithAttributeAll", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributeAll() {
    	try {
			return zzDeviceService.selectDeviceWithAttributeAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
    
    @RequestMapping(value = "/selectDeviceWithAttributePage", method = RequestMethod.POST)
    public SysResult selectDeviceWithAttributePage(@RequestParam(name="keyword",required=false) String keyword,
            @RequestParam(name="pageNo",required=false) Integer pageNo,
            @RequestParam(name="pageSize",required=false) Integer pageSize) {
    	try {
			return zzDeviceService.selectDeviceWithAttributePage(keyword, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(ErrorUtil.CODE5000, e.getMessage(), null);
		}
    }
}
